/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reproductorExpandible.tags;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author JONATHAN
 */
public interface Informacion
{
    public void load(InputStream input) throws IOException, UnsupportedAudioFileException;

    public void load(URL input) throws IOException, UnsupportedAudioFileException;

    public void load(File input) throws IOException, UnsupportedAudioFileException;
public int getSamplingRate();

    /**
     * Get Nominal Bitrate
     *
     * @return bitrate in bps
     */
    public int getBitRate();

    /**
     * Get channels.
     *
     * @return channels
     */
    public int getCanales();

    /**
     * Get play time in seconds.
     *
     * @return play time in seconds
     */
    public double getTiempo_en_segundos();

    /**
     * Get the title of the song.
     *
     * @return the title of the song
     */
    public String getTitulo();

    /**
     * Get the artist that performed the song
     *
     * @return the artist that performed the song
     */
    public String getArtista();

    /**
     * Get the name of the album upon which the song resides
     *
     * @return the album name
     */
    public String getAlbum();

    /**
     * Get the track number of this track on the album
     *
     * @return the track number
     */
    public int getTrack();

    /**
     * Get the genre string of the music
     *
     * @return the genre string
     */
    public String getGenero();

    /**
     * Get the year the track was released
     *
     * @return the year the track was released
     */
    public String getaño();
}
