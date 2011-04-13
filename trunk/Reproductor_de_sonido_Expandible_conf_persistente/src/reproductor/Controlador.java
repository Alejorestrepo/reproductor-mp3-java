
package reproductor;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * This interface defines player controls available.  
 */
public interface Controlador
{
    /**
     * Open inputstream to play.
     * @param in
     * @throws BasicPlayerException
     */
    public void abrir(InputStream in) throws ReproductorExcepcion;

    /**
     * Open file to play.
     * @param file
     * @throws BasicPlayerException
     */
    public void abrir(File file) throws ReproductorExcepcion;

    /**
     * Open URL to play.
     * @param url
     * @throws BasicPlayerException
     */
    public void abrir(URL url) throws ReproductorExcepcion;

    /**
     * Skip bytes.
     * @param bytes
     * @return bytes skipped according to audio frames constraint.
     * @throws BasicPlayerException
     */
    public long buscar_saltar(long bytes) throws ReproductorExcepcion;

    /**
     * Start playback.
     * @throws BasicPlayerException
     */
    public void reproducir() throws ReproductorExcepcion;

    /**
     * Stop playback. 
     * @throws BasicPlayerException
     */
    public void parar() throws ReproductorExcepcion;

    /**
     * Pause playback. 
     * @throws BasicPlayerException
     */
    public void pausar() throws ReproductorExcepcion;

    /**
     * Resume playback. 
     * @throws BasicPlayerException
     */
    public void resumir() throws ReproductorExcepcion;

    /**
     * Sets Pan (Balance) value.
     * Linear scale : -1.0 <--> +1.0
     * @param pan value from -1.0 to +1.0
     * @throws BasicPlayerException
     */
    public void setPan(double pan) throws ReproductorExcepcion;

    /**
     * Sets Gain value.
     * Linear scale 0.0  <-->  1.0
     * @param gain value from 0.0 to 1.0
     * @throws BasicPlayerException
     */
    public void setGain(double gain) throws ReproductorExcepcion;
}
