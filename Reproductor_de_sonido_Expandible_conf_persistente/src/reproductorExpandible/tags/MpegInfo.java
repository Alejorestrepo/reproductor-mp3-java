/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductorExpandible.tags;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 *
 * @author JONATHAN
 */
public class MpegInfo implements Informacion {

    private int canales = -1;
    private String channelsMode = null;
    private String version = null;
    protected int rate = 0;
    private String layer = null;
    private String emphasis = null;
    private int nominalbitrate = 0;
    private double segundos = 0.0;
    private long milisegundos=0;
    private String location = null;
    private long tamaño = 0;
    protected boolean copyright = false;
    protected boolean crc = false;
    protected boolean original = false;
    protected boolean priv = false;
    protected boolean vbr = false;
    private int track = -1;
    private String año = null;
    private String genero = null;
    private String titulo = null;
    private String artista = null;
    private String album = null;
    public MpegInfo() {
        super();
    }
    public void load(InputStream input) throws IOException, UnsupportedAudioFileException {
        loadInfo(input);
    }

    public void load(URL input) throws IOException, UnsupportedAudioFileException {
        location = input.toString();
        loadInfo(input);
    }

    public void load(File input) throws IOException, UnsupportedAudioFileException {
        tamaño = input.length();
        location = input.getPath();
        loadInfo(input);
    }

    public int getSamplingRate() {
        return rate;
    }

    public int getBitRate() {
        return nominalbitrate;
    }

    public int getCanales() {
        return canales;
    }

    public double getTiempo_en_segundos() {
        return segundos;
    }

    public String getAlbum() {
        return album;
    }

    public int getTrack() {
        return track;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public String getaño() {
        return año;
    }

    public boolean getVBR() {
        return vbr;
    }

    public String getVersion() {
        return version;
    }

    public String getEmphasis() {
        return emphasis;
    }

    public boolean getCopyright() {
        return copyright;
    }

    public boolean getCRC() {
        return crc;
    }

    public boolean getOriginal() {
        return original;
    }

    public String getLayer() {
        return layer;
    }

    public long getTamaño() {
        return tamaño;
    }

    public String getDireccion() {
        return location;
    }

    private void loadInfo(File input) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
    }

    private void loadInfo(InputStream input) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
    }

    private void loadInfo(URL input) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
        loadShoutastInfo(aff);
    }

    private void loadInfo(AudioFileFormat aff) throws UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("mp3")) {
            throw new UnsupportedAudioFileException("Not MP3 audio format");
        }
        if (aff instanceof TAudioFileFormat) {
            Map props = ((TAudioFileFormat) aff).properties();
            if (props.containsKey("mp3.channels")) {
                canales = ((Integer) props.get("mp3.channels")).intValue();
            }
            if (props.containsKey("mp3.frequency.hz")) {
                rate = ((Integer) props.get("mp3.frequency.hz")).intValue();
            }
            if (props.containsKey("mp3.bitrate.nominal.bps")) {
                nominalbitrate = ((Integer) props.get("mp3.bitrate.nominal.bps")).intValue();
            }
            if (props.containsKey("mp3.version.layer")) {
                layer = "Layer " + props.get("mp3.version.layer");
            }
            if (props.containsKey("mp3.version.mpeg")) {
                version = (String) props.get("mp3.version.mpeg");
                if (version.equals("1")) {
                    version = "MPEG1";
                } else if (version.equals("2")) {
                    version = "MPEG2-LSF";
                } else if (version.equals("2.5")) {
                    version = "MPEG2.5-LSF";
                }
            }
            if (props.containsKey("mp3.mode")) {
                int mode = ((Integer) props.get("mp3.mode")).intValue();
                if (mode == 0) {
                    channelsMode = "Stereo";
                } else if (mode == 1) {
                    channelsMode = "Joint Stereo";
                } else if (mode == 2) {
                    channelsMode = "Dual Channel";
                } else if (mode == 3) {
                    channelsMode = "Single Channel";
                }
            }
            if (props.containsKey("mp3.crc")) {
                crc = ((Boolean) props.get("mp3.crc")).booleanValue();
            }
            if (props.containsKey("mp3.vbr")) {
                vbr = ((Boolean) props.get("mp3.vbr")).booleanValue();
            }
            if (props.containsKey("mp3.copyright")) {
                copyright = ((Boolean) props.get("mp3.copyright")).booleanValue();
            }
            if (props.containsKey("mp3.original")) {
                original = ((Boolean) props.get("mp3.original")).booleanValue();
            }
            emphasis = "none";
            if (props.containsKey("title")) {
                titulo = (String) props.get("title");
            }
            if (props.containsKey("author")) {
                artista = (String) props.get("author");
            }
            if (props.containsKey("album")) {
                album = (String) props.get("album");
            }
            if (props.containsKey("date")) {
                año = (String) props.get("date");
            }
            if (props.containsKey("duration")) {
                milisegundos=(Long) props.get("duration");
                segundos=(((int) ((Long) props.get("duration") / 10000)) / 100.0);
            }
            if (props.containsKey("mp3.id3tag.genre")) {
                genero = (String) props.get("mp3.id3tag.genre");
            }
            if (props.containsKey("mp3.id3tag.track")) {
                try {
                    track = Integer.parseInt((String) props.get("mp3.id3tag.track"));
                } catch (NumberFormatException e1) {
                    // Not a number
                }
            }
        }
    }

    protected void loadShoutastInfo(AudioFileFormat aff) throws IOException, UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("mp3")) throw new UnsupportedAudioFileException("Not MP3 audio format");
        if (aff instanceof TAudioFileFormat) {
            Map props = ((TAudioFileFormat) aff).properties();
            Iterator it = props.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                if (key.startsWith("mp3.shoutcast.metadata.")) {
                    String value = (String) props.get(key);
                    key = key.substring(23, key.length());
                    if (key.equalsIgnoreCase("icy-name")) {
                        titulo = value;
                    } else if (key.equalsIgnoreCase("icy-genre")) {
                        genero = value;
                    }
                }
            }
        }
    }

    /**
     * @return the milisegundos
     */
    public long getMilisegundos() {
        return milisegundos;
    }

    /**
     * @return the channelsMode
     */
    public String getChannelsMode() {
        return channelsMode;
    }
}
