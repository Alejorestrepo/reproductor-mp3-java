
package reproductor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.spi.PropertiesContainer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * Based on JavaSound API.
 * It has been successfully tested under J2SE 1.3.x, 1.4.x and 1.5.x.
 */
public class Reproductor implements Controlador, Runnable
{
    public static int EXTERNAL_BUFFER_SIZE = 4000 * 4;
    public static int SKIP_INACCURACY_SIZE = 1200;
    protected Thread mi_hilo = null;
    protected Object mi_fuente_de_Datos;
    protected AudioInputStream m_encodedaudioInputStream;
    protected int encodedLength = -1;
    protected AudioInputStream m_audioInputStream;
    protected AudioFileFormat m_audioFileFormat;
    protected SourceDataLine m_line;
    protected FloatControl m_gainControl;
    protected FloatControl m_panControl;
    protected String m_mixerName = null;
    private int m_lineCurrentBufferSize = -1;
    private int lineBufferSize = -1;
    private long threadSleep = -1;
    private static Log log = LogFactory.getLog(Reproductor.class);
    /**
     * These variables are used to distinguish stopped, paused, playing states.
     * We need them to control Thread.
     */
    public static final int DESCONOCIDO = -1;
    public static final int REPRODUCIENDO = 0;
    public static final int PAUSADO = 1;
    public static final int PARADO = 2;
    public static final int ABIERTO = 3;
    public static final int SALTANDO = 4;
    private int m_status = DESCONOCIDO;
    // Listeners to be notified.
    private Collection m_listeners = null;
    private Map empty_map = new HashMap();

    /**
     * Constructs a Reproductor.
     */
    public Reproductor()
    {
        mi_fuente_de_Datos = null;
        m_listeners = new ArrayList();
        reset();
    }

    protected void reset()
    {
        m_status = DESCONOCIDO;
        if (m_audioInputStream != null)
        {
            synchronized (m_audioInputStream)
            {
                closeStream();
            }
        }
        m_audioInputStream = null;
        m_audioFileFormat = null;
        m_encodedaudioInputStream = null;
        encodedLength = -1;
        if (m_line != null)
        {
            m_line.stop();
            m_line.close();
            m_line = null;
        }
        m_gainControl = null;
        m_panControl = null;
    }

    /**
     * Add listener to be notified.
     * @param bpl
     */
    public void addBasicPlayerListener(ReproductorLanzador bpl)
    {
        m_listeners.add(bpl);
    }

    /**
     * Return registered listeners.
     * @return
     */
    public Collection getListeners()
    {
        return m_listeners;
    }

    /**
     * Remove registered listener.
     * @param bpl
     */
    public void removeBasicPlayerListener(ReproductorLanzador bpl)
    {
        if (m_listeners != null)
        {
            m_listeners.remove(bpl);
        }
    }

    /**
     * Set SourceDataLine buffer size. It affects audio latency.
     * (the delay between line.write(data) and real sound).
     * Minimum value should be over 10000 bytes.
     * @param size -1 means maximum buffer size available.
     */
    public void setLineBufferSize(int size)
    {
        lineBufferSize = size;
    }

    /**
     * Return SourceDataLine buffer size.
     * @return -1 maximum buffer size.
     */
    public int getLineBufferSize()
    {
        return lineBufferSize;
    }
    
    /**
     * Return SourceDataLine current buffer size.
     * @return
     */
    public int getLineCurrentBufferSize()
    {
        return m_lineCurrentBufferSize;
    }

    /**
     * Set thread sleep time.
     * Default is -1 (no sleep time).
     * @param time in milliseconds.
     */
    public void setSleepTime(long time)
    {
        threadSleep = time;
    }

    /**
     * Return thread sleep time in milliseconds.
     * @return -1 means no sleep time.
     */
    public long getSleepTime()
    {
        return threadSleep;
    }

    /**
     * Returns Reproductor status.
     * @return status
     */
    public int getStatus()
    {
        return m_status;
    }

    /**
     * Open file to play.
     */
    public void abrir(File file) throws ReproductorExcepcion
    {
        log.info("open(" + file + ")");
        if (file != null)
        {
            mi_fuente_de_Datos = file;
            initAudioInputStream();
        }
    }

    /**
     * Open URL to play.
     */
    public void abrir(URL url) throws ReproductorExcepcion
    {
        log.info("open(" + url + ")");
        if (url != null)
        {
            mi_fuente_de_Datos = url;
            initAudioInputStream();
        }
    }

    /**
     * Open inputstream to play.
     */
    public void abrir(InputStream inputStream) throws ReproductorExcepcion
    {
        log.info("open(" + inputStream + ")");
        if (inputStream != null)
        {
            mi_fuente_de_Datos = inputStream;
            initAudioInputStream();
        }
    }

    /**
     * Inits AudioInputStream and AudioFileFormat from the data source.
     * @throws ReproductorException
     */
    protected void initAudioInputStream() throws ReproductorExcepcion
    {
        try
        {
            reset();
            notifyEvent(ReproductorEvento.ABRIENDO, getEncodedStreamPosition(), -1, mi_fuente_de_Datos);
            if (mi_fuente_de_Datos instanceof URL)
            {
                initAudioInputStream((URL) mi_fuente_de_Datos);
            }
            else if (mi_fuente_de_Datos instanceof File)
            {
                initAudioInputStream((File) mi_fuente_de_Datos);
            }
            else if (mi_fuente_de_Datos instanceof InputStream)
            {
                initAudioInputStream((InputStream) mi_fuente_de_Datos);
            }
            createLine();
            // Notificar a listeners con las características de AudioFileFormat.
            Map properties = null;
            if (m_audioFileFormat instanceof TAudioFileFormat)
            {
                // Tritonus SPI compliant audio file format.
                properties = ((TAudioFileFormat) m_audioFileFormat).properties();
                // Clone the Map because it is not mutable.
                properties = deepCopy(properties);
            }
            else properties = new HashMap();
            // Add JavaSound properties.
            if (m_audioFileFormat.getByteLength() > 0) properties.put("audio.length.bytes", new Integer(m_audioFileFormat.getByteLength()));
            if (m_audioFileFormat.getFrameLength() > 0) properties.put("audio.length.frames", new Integer(m_audioFileFormat.getFrameLength()));
            if (m_audioFileFormat.getType() != null) properties.put("audio.type", (m_audioFileFormat.getType().toString()));
            // Audio format.
            AudioFormat audioFormat = m_audioFileFormat.getFormat();
            if (audioFormat.getFrameRate() > 0) properties.put("audio.framerate.fps", new Float(audioFormat.getFrameRate()));
            if (audioFormat.getFrameSize() > 0) properties.put("audio.framesize.bytes", new Integer(audioFormat.getFrameSize()));
            if (audioFormat.getSampleRate() > 0) properties.put("audio.samplerate.hz", new Float(audioFormat.getSampleRate()));
            if (audioFormat.getSampleSizeInBits() > 0) properties.put("audio.samplesize.bits", new Integer(audioFormat.getSampleSizeInBits()));
            if (audioFormat.getChannels() > 0) properties.put("audio.channels", new Integer(audioFormat.getChannels()));
            if (audioFormat instanceof TAudioFormat)
            {
                // Tritonus SPI compliant audio format.
                Map addproperties = ((TAudioFormat) audioFormat).properties();
                properties.putAll(addproperties);
            }
            // Add SourceDataLine
            properties.put("basicplayer.sourcedataline", m_line);
            Iterator it = m_listeners.iterator();
            while (it.hasNext())
            {
                ReproductorLanzador bpl = (ReproductorLanzador) it.next();
                bpl.abierto(properties);
            }
            m_status = ABIERTO;
            notifyEvent(ReproductorEvento.ABIERTO, getEncodedStreamPosition(), -1, null);
        }
        catch (LineUnavailableException e)
        {
            throw new ReproductorExcepcion(e);
        }
        catch (UnsupportedAudioFileException e)
        {
            throw new ReproductorExcepcion(e);
        }
        catch (IOException e)
        {
            throw new ReproductorExcepcion(e);
        }
    }

    /**
     * Inits Audio ressources from file.
     */
    protected void initAudioInputStream(File file) throws UnsupportedAudioFileException, IOException
    {
        m_audioInputStream = AudioSystem.getAudioInputStream(file);
        m_audioFileFormat = AudioSystem.getAudioFileFormat(file);
    }

    /**
     * Inits Audio ressources from URL.
     */
    protected void initAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException
    {
        m_audioInputStream = AudioSystem.getAudioInputStream(url);
        m_audioFileFormat = AudioSystem.getAudioFileFormat(url);
    }

    /**
     * Inits Audio ressources from InputStream.
     */
    protected void initAudioInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException
    {
        m_audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        m_audioFileFormat = AudioSystem.getAudioFileFormat(inputStream);
    }

    /**
     * Inits Audio ressources from AudioSystem.<br>
     */
    protected void initLine() throws LineUnavailableException
    {
        log.info("initLine()");
        if (m_line == null) createLine();
        if (!m_line.isOpen())
        {
            openLine();
        }
        else
        {
            AudioFormat lineAudioFormat = m_line.getFormat();
            AudioFormat audioInputStreamFormat = m_audioInputStream == null ? null : m_audioInputStream.getFormat();
            if (!lineAudioFormat.equals(audioInputStreamFormat))
            {
                m_line.close();
                openLine();
            }
        }
    }

    /**
     * Inits a DateLine.<br>
     *
     * We check if the line supports Gain and Pan controls.
     *
     * From the AudioInputStream, i.e. from the sound file, we
     * fetch information about the format of the audio data. These
     * information include the sampling frequency, the number of
     * channels and the size of the samples. There information
     * are needed to ask JavaSound for a suitable output line
     * for this audio file.
     * Furthermore, we have to give JavaSound a hint about how
     * big the internal buffer for the line should be. Here,
     * we say AudioSystem.NOT_SPECIFIED, signaling that we don't
     * care about the exact size. JavaSound will use some default
     * value for the buffer size.
     */
    protected void createLine() throws LineUnavailableException
    {
        log.info("Creando Linea");
        if (m_line == null)
        {
            AudioFormat sourceFormat = m_audioInputStream.getFormat();
            log.info("Creando Linea : Source format : " + sourceFormat.toString());
            int nSampleSizeInBits = sourceFormat.getSampleSizeInBits();
            if (nSampleSizeInBits <= 0) nSampleSizeInBits = 16;
            if ((sourceFormat.getEncoding() == AudioFormat.Encoding.ULAW) || (sourceFormat.getEncoding() == AudioFormat.Encoding.ALAW)) nSampleSizeInBits = 16;
            if (nSampleSizeInBits != 8) nSampleSizeInBits = 16;
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), nSampleSizeInBits, sourceFormat.getChannels(), sourceFormat.getChannels() * (nSampleSizeInBits / 8), sourceFormat.getSampleRate(), false);
            log.info("Creando Linea : Target format: " + targetFormat);
            // Keep a reference on encoded stream to progress notification.
            m_encodedaudioInputStream = m_audioInputStream;
            try
            {
                // Get total length in bytes of the encoded stream.
                encodedLength = m_encodedaudioInputStream.available();
            }
            catch (IOException e)
            {
                log.error("Cannot get m_encodedaudioInputStream.available()", e);
            }
            // Create decoded stream.
            m_audioInputStream = AudioSystem.getAudioInputStream(targetFormat, m_audioInputStream);
            AudioFormat audioFormat = m_audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
            Mixer mixer = getMixer(m_mixerName);
            if (mixer != null)
            {
                log.info("Mixer : "+mixer.getMixerInfo().toString());
                m_line = (SourceDataLine) mixer.getLine(info);
            }
            else 
            {
                m_line = (SourceDataLine) AudioSystem.getLine(info);
                m_mixerName = null;
            }
            log.info("Linea : " + m_line.toString());
            log.debug("Informacion de linea : " + m_line.getLineInfo().toString());
            log.debug("Line AudioFormat: " + m_line.getFormat().toString());
        }
    }

    /**
     * Opens the line.
     */
    protected void openLine() throws LineUnavailableException
    {
        if (m_line != null)
        {
            AudioFormat audioFormat = m_audioInputStream.getFormat();
            int buffersize = lineBufferSize;
            if (buffersize <= 0) buffersize = m_line.getBufferSize();
            m_lineCurrentBufferSize = buffersize;
            m_line.open(audioFormat, buffersize);
            log.info("Linea abierta : BufferSize=" + buffersize);
            /*-- Display supported controls --*/
            Control[] c = m_line.getControls();
            for (int p = 0; p < c.length; p++)
            {
                log.debug("Controls : " + c[p].toString());
            }
            /*-- Is Gain Control supported ? --*/
            if (m_line.isControlSupported(FloatControl.Type.MASTER_GAIN))
            {
                m_gainControl = (FloatControl) m_line.getControl(FloatControl.Type.MASTER_GAIN);
                log.info("Master Gain Control : [" + m_gainControl.getMinimum() + "," + m_gainControl.getMaximum() + "] " + m_gainControl.getPrecision());
            }
            /*-- Is Pan control supported ? --*/
            if (m_line.isControlSupported(FloatControl.Type.PAN))
            {
                m_panControl = (FloatControl) m_line.getControl(FloatControl.Type.PAN);
                log.info("Pan Control : [" + m_panControl.getMinimum() + "," + m_panControl.getMaximum() + "] " + m_panControl.getPrecision());
            }
        }
    }

    /**
     * Stops the playback.<br>
     *
     * Player Status = STOPPED.<br>
     * Thread should free Audio ressources.
     */
    protected void stopPlayback()
    {
        if ((m_status == REPRODUCIENDO) || (m_status == PAUSADO))
        {
            if (m_line != null)
            {
                m_line.flush();
                m_line.stop();
            }
            m_status = PARADO;
            notifyEvent(ReproductorEvento.PARADO, getEncodedStreamPosition(), -1, null);
            synchronized (m_audioInputStream)
            {
                closeStream();
            }
            log.info("stopPlayback() completado");
        }
    }

    /**
     * Pauses the playback.<br>
     *
     * Player Status = PAUSED.
     */
    protected void pausePlayback()
    {
        if (m_line != null)
        {
            if (m_status == REPRODUCIENDO)
            {
                m_line.flush();
                m_line.stop();
                m_status = PAUSADO;
                log.info("pausePlayback() completado");
                notifyEvent(ReproductorEvento.PAUSADO, getEncodedStreamPosition(), -1, null);
            }
        }
    }

    /**
     * Resumes the playback.<br>
     *
     * Player Status = PLAYING.
     */
    protected void resumePlayback()
    {
        if (m_line != null)
        {
            if (m_status == PAUSADO)
            {
                m_line.start();
                m_status = REPRODUCIENDO;
                log.info("resumePlayback() completado");
                notifyEvent(ReproductorEvento.RESUMIDO, getEncodedStreamPosition(), -1, null);
            }
        }
    }

    /**
     * Starts playback.
     */
    protected void startPlayback() throws ReproductorExcepcion
    {
        if (m_status == PARADO) initAudioInputStream();
        if (m_status == ABIERTO)
        {
            log.info("startPlayback llamado");
            if (!(mi_hilo == null || !mi_hilo.isAlive()))
            {
                log.info("ADVERTENCIA: Hilo antiguo aún en marcha!!");
                int cnt = 0;
                while (m_status != ABIERTO)
                {
                    try
                    {
                        if (mi_hilo != null)
                        {
                            log.info("Esperando ... " + cnt);
                            cnt++;
                            Thread.sleep(1000);
                            if (cnt > 2)
                            {
                                mi_hilo.interrupt();
                            }
                        }
                    }
                    catch (InterruptedException e)
                    {
                        throw new ReproductorExcepcion(ReproductorExcepcion.WAITERROR, e);
                    }
                }
            }
            // Open SourceDataLine.
            try
            {
                initLine();
            }
            catch (LineUnavailableException e)
            {
                throw new ReproductorExcepcion(ReproductorExcepcion.CANNOTINITLINE, e);
            }
            log.info("Creando nuevo hilo");
            mi_hilo = new Thread(this, "BasicPlayer");
            mi_hilo.start();
            if (m_line != null)
            {
                m_line.start();
                m_status = REPRODUCIENDO;
                notifyEvent(ReproductorEvento.REPRODUCIENDO, getEncodedStreamPosition(), -1, null);
            }
        }
    }

    /**
     * Main loop.
     *
     * Player Status == STOPPED || SEEKING => End of Thread + Freeing Audio Ressources.<br>
     * Player Status == PLAYING => Audio stream data sent to Audio line.<br>
     * Player Status == PAUSED => Waiting for another status.
     */
    public void run()
    {
        log.info("Hilo en curso");
        int nBytesRead = 1;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
        // Lock stream while playing.
        synchronized (m_audioInputStream)
        {
            // Main play/pause loop.
            while ((nBytesRead != -1) && (m_status != PARADO) && (m_status != SALTANDO) && (m_status != DESCONOCIDO))
            {
                if (m_status == REPRODUCIENDO)
                {
                    // Play.
                    try
                    {
                        nBytesRead = m_audioInputStream.read(abData, 0, abData.length);
                        if (nBytesRead >= 0)
                        {
                            byte[] pcm = new byte[nBytesRead];
                            System.arraycopy(abData, 0, pcm, 0, nBytesRead);
                            if (m_line.available() >= m_line.getBufferSize()) log.debug("Underrun : "+m_line.available()+"/"+m_line.getBufferSize());
                            int nBytesWritten = m_line.write(abData, 0, nBytesRead);
                            // Compute position in bytes in encoded stream.
                            int nEncodedBytes = getEncodedStreamPosition();
                            // Notify listeners
                            Iterator it = m_listeners.iterator();
                            while (it.hasNext())
                            {
                                ReproductorLanzador bpl = (ReproductorLanzador) it.next();
                                if (m_audioInputStream instanceof PropertiesContainer)
                                {
                                    // Pass audio parameters such as instant bitrate, ...
                                    Map properties = ((PropertiesContainer) m_audioInputStream).properties();
                                    bpl.progreso(nEncodedBytes, m_line.getMicrosecondPosition(), pcm, properties);
                                }
                                else bpl.progreso(nEncodedBytes, m_line.getMicrosecondPosition(), pcm, empty_map);
                            }
                        }
                    }
                    catch (IOException e)
                    {
                        log.error("Hilo no puede run()", e);
                        m_status = PARADO;
                        notifyEvent(ReproductorEvento.PARADO, getEncodedStreamPosition(), -1, null);
                    }
                    // Nice CPU usage.
                    if (threadSleep > 0)
                    {
                        try
                        {
                            Thread.sleep(threadSleep);
                        }
                        catch (InterruptedException e)
                        {
                            log.error("Hilo no puede sleep(" + threadSleep + ")", e);
                        }
                    }
                }
                else
                {
                    // Pause
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        log.error("Hilo no puede sleep(1000)", e);
                    }
                }
            }
            // Free audio resources.
            if (m_line != null)
            {
                m_line.drain();
                m_line.stop();
                m_line.close();
                m_line = null;
            }
            // Notification of "End Of Media"
            if (nBytesRead == -1)
            {
                notifyEvent(ReproductorEvento.EOM, getEncodedStreamPosition(), -1, null);
            }
            // Close stream.
            closeStream();
        }
        m_status = PARADO;
        notifyEvent(ReproductorEvento.PARADO, getEncodedStreamPosition(), -1, null);
        log.info("Hilo completado");
    }

    /**
     * Skip bytes in the File inputstream.
     * It will skip N frames matching to bytes, so it will never skip given bytes length exactly.
     * @param bytes
     * @return value>0 for File and value=0 for URL and InputStream
     * @throws ReproductorException
     */
    protected long skipBytes(long bytes) throws ReproductorExcepcion
    {
        long totalSkipped = 0;
        if (mi_fuente_de_Datos instanceof File)
        {
            log.info("Bytes a saltar : " + bytes);
            int previousStatus = m_status;
            m_status = SALTANDO;
            long skipped = 0;
            try
            {
                synchronized (m_audioInputStream)
                {
                    notifyEvent(ReproductorEvento.SALTANDO, getEncodedStreamPosition(), -1, null);
                    initAudioInputStream();
                    if (m_audioInputStream != null)
                    {
                        // Loop until bytes are really skipped.
                        while (totalSkipped < (bytes - SKIP_INACCURACY_SIZE))
                        {
                            skipped = m_audioInputStream.skip(bytes - totalSkipped);
                            if (skipped == 0) break;
                            totalSkipped = totalSkipped + skipped;
                            log.info("Saltado : " + totalSkipped + "/" + bytes);
                            if (totalSkipped == -1) throw new ReproductorExcepcion(ReproductorExcepcion.SKIPNOTSUPPORTED);
                        }
                    }
                }
                notifyEvent(ReproductorEvento.SALTADO, getEncodedStreamPosition(), -1, null);
                m_status = ABIERTO;
                if (previousStatus == REPRODUCIENDO) startPlayback();
                else if (previousStatus == PAUSADO)
                {
                    startPlayback();
                    pausePlayback();
                }
            }
            catch (IOException e)
            {
                throw new ReproductorExcepcion(e);
            }
        }
        return totalSkipped;
    }

    /**
     * Notify listeners about a ReproductorEvent.
     * @param code event code.
     * @param position in the stream when the event occurs.
     */
    protected void notifyEvent(int code, int position, double value, Object description)
    {
        ReproductorLanzarEvento trigger = new ReproductorLanzarEvento(code, position, value, description, new ArrayList(m_listeners), this);
        trigger.start();
    }

    protected int getEncodedStreamPosition()
    {
        int nEncodedBytes = -1;
        if (mi_fuente_de_Datos instanceof File)
        {
            try
            {
                if (m_encodedaudioInputStream != null)
                {
                    nEncodedBytes = encodedLength - m_encodedaudioInputStream.available();
                }
            }
            catch (IOException e)
            {
                //log.debug("Cannot get m_encodedaudioInputStream.available()",e);
            }
        }
        return nEncodedBytes;
    }

    protected void closeStream()
    {
        // Close stream.
        try
        {
            if (m_audioInputStream != null)
            {
                m_audioInputStream.close();
                log.info("Flujo cerrado");
            }
        }
        catch (IOException e)
        {
            log.info("No se puede cerrar el flujo", e);
        }
    }

    /**
     * Returns true if Gain control is supported.
     */
    public boolean hasGainControl()
    {
        if (m_gainControl == null)
        {
            // Try to get Gain control again (to support J2SE 1.5)
            if ( (m_line != null) && (m_line.isControlSupported(FloatControl.Type.MASTER_GAIN))) m_gainControl = (FloatControl) m_line.getControl(FloatControl.Type.MASTER_GAIN);
        }
        return m_gainControl != null;
    }

    /**
     * Returns Gain value.
     */
    public float getGainValue()
    {
        if (hasGainControl())
        {
            return m_gainControl.getValue();
        }
        else
        {
            return 0.0F;
        }
    }

    /**
     * Gets max Gain value.
     */
    public float getMaximumGain()
    {
        if (hasGainControl())
        {
            return m_gainControl.getMaximum();
        }
        else
        {
            return 0.0F;
        }
    }

    /**
     * Gets min Gain value.
     */
    public float getMinimumGain()
    {
        if (hasGainControl())
        {
            return m_gainControl.getMinimum();
        }
        else
        {
            return 0.0F;
        }
    }

    /**
     * Returns true if Pan control is supported.
     */
    public boolean hasPanControl()
    {
        if (m_panControl == null)
        {
            // Try to get Pan control again (to support J2SE 1.5)
            if ((m_line != null)&& (m_line.isControlSupported(FloatControl.Type.PAN))) m_panControl = (FloatControl) m_line.getControl(FloatControl.Type.PAN);
        }
        return m_panControl != null;
    }

    /**
     * Returns Pan precision.
     */
    public float getPrecision()
    {
        if (hasPanControl())
        {
            return m_panControl.getPrecision();
        }
        else
        {
            return 0.0F;
        }
    }

    /**
     * Returns Pan value.
     */
    public float getPan()
    {
        if (hasPanControl())
        {
            return m_panControl.getValue();
        }
        else
        {
            return 0.0F;
        }
    }

    /**
     * Deep copy of a Map.
     * @param src
     * @return
     */
    protected Map deepCopy(Map src)
    {
        HashMap map = new HashMap();
        if (src != null)
        {
            Iterator it = src.keySet().iterator();
            while (it.hasNext())
            {
                Object key = it.next();
                Object value = src.get(key);
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * @see javazoom.jlgui.Reproductor.BasicController#seek(long)
     */
    public long buscar_saltar(long bytes) throws ReproductorExcepcion
    {
        return skipBytes(bytes);
    }

    /**
     * @see javazoom.jlgui.Reproductor.BasicController#play()
     */
    public void reproducir() throws ReproductorExcepcion
    {
        startPlayback();
    }

    /**
     * @see javazoom.jlgui.Reproductor.BasicController#stop()
     */
    public void parar() throws ReproductorExcepcion
    {
        stopPlayback();
    }

    /**
     * @see javazoom.jlgui.Reproductor.BasicController#pause()
     */
    public void pausar() throws ReproductorExcepcion
    {
        pausePlayback();
    }

    /**
     * @see javazoom.jlgui.Reproductor.BasicController#resume()
     */
    public void resumir() throws ReproductorExcepcion
    {
        resumePlayback();
    }

    /**
     * Sets Pan value.
     * Line should be opened before calling this method.
     * Linear scale : -1.0 <--> +1.0
     */
    public void setPan(double fPan) throws ReproductorExcepcion
    {
        if (hasPanControl())
        {
            log.debug("Pan : " + fPan);
            m_panControl.setValue((float) fPan);
            notifyEvent(ReproductorEvento.PAN, getEncodedStreamPosition(), fPan, null);
        }
        else throw new ReproductorExcepcion(ReproductorExcepcion.PANCONTROLNOTSUPPORTED);
    }

    /**
     * Sets Gain value.
     * Line should be opened before calling this method.
     * Linear scale 0.0  <-->  1.0
     * Threshold Coef. : 1/2 to avoid saturation.
     */
    public void setGain(double fGain) throws ReproductorExcepcion
    {
        if (hasGainControl())
        {
            double minGainDB = getMinimumGain();
            double ampGainDB = ((10.0f / 20.0f) * getMaximumGain()) - getMinimumGain();
            double cste = Math.log(10.0) / 20;
            double valueDB = minGainDB + (1 / cste) * Math.log(1 + (Math.exp(cste * ampGainDB) - 1) * fGain);
            log.debug("Gain : " + valueDB);
            m_gainControl.setValue((float) valueDB);
            notifyEvent(ReproductorEvento.GAIN, getEncodedStreamPosition(), fGain, null);
        }
        else throw new ReproductorExcepcion(ReproductorExcepcion.GAINCONTROLNOTSUPPORTED);
    }
    
    public List getMixers()
    {
        ArrayList mixers = new ArrayList();
        Mixer.Info[] mInfos = AudioSystem.getMixerInfo();
        if (mInfos != null)
        {
            for (int i = 0; i < mInfos.length; i++)
            {
                Line.Info lineInfo = new Line.Info(SourceDataLine.class);
                Mixer mixer = AudioSystem.getMixer(mInfos[i]);
                if (mixer.isLineSupported(lineInfo))
                {
                    mixers.add(mInfos[i].getName());
                }
            }            
        }
        return mixers;        
    }
    
    public Mixer getMixer(String name)
    {
        Mixer mixer = null;
        if (name != null)
        {
            Mixer.Info[] mInfos = AudioSystem.getMixerInfo();
            if (mInfos != null)
            {
                for (int i = 0; i < mInfos.length; i++)
                {
                    if (mInfos[i].getName().equals(name))
                    {
                        mixer = AudioSystem.getMixer(mInfos[i]);
                        break;
                    }
                }            
            }            
        }
        return mixer;
    }
    
    public String getMixerName()
    {
        return m_mixerName;
    }
    
    public void setMixerName(String name)
    {
        m_mixerName = name;
    }
}
