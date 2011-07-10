/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductorExpandible;

import elementos_de_control.Direcciones;
import elementos_de_control.ListaDobleConOrden;
import elementos_de_control.NodoDoble;
import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jlgui.player.amp.visual.ui.SpectrumTimeAnalyzer;
import reproductor.Controlador;
import reproductor.Reproductor;
import reproductor.ReproductorEvento;
import reproductor.ReproductorExcepcion;
import reproductor.ReproductorLanzador;
import reproductorExpandible.tags.*;

/**
 *
 * @author JONATHAN
 */
public class Libreria implements ReproductorLanzador{

    Properties propiedades_conf = new Properties();
    String canonicalPath;
    static Tabla tabla;
    private static Reproductor ReproductorBasico;
    public int Contador_de_celda = 0, control1 = 0, veces = 0;
    public static int Pista = 0;
    static File tamañoarchivo;
    Long duration = null;
    public MpegInfo mpeg;
    public OggVorbisInfo ogg;
    public FlacInfo fla;
    public APEInfo ape;
    public WavInfo wav;
    public Map audioInfo = null;
    JSlider Volumen;
    JCheckBoxMenuItem Modo_Presentacion;
    JPanel Panel_espectro;
    Equalizador equalizar;
    public long secondsAmount = 0;
    boolean repetir;
    GUIReproductor abc;
    public static String[] extencion_archivo;
    public static String[] exten;

    public Libreria(GUIReproductor aThis) {
        abc = aThis;
        mpeg = new MpegInfo();
        ogg = new OggVorbisInfo();
        fla = new FlacInfo();
        ape = new APEInfo();
        wav = new WavInfo();
        ReproductorBasico = new Reproductor();
        ReproductorBasico.addBasicPlayerListener(this);
        Volumen = abc.Volumen;
        Modo_Presentacion = abc.Modo_Presentacion;
        Panel_espectro = abc.Panel_espectro;
        repetir = abc.repetir;
        tabla = new Tabla(abc);
        equalizar = new Equalizador();
    }

    public String DevolverFormateado(int horas, int minutofinal, int segundofinal) {
        String hora, minutos, segundo;
        hora = String.valueOf(horas);
        minutos = String.valueOf(minutofinal);
        segundo = String.valueOf(segundofinal);
        if (hora.length() == 1) {
            hora = "0" + hora;
        }
        if (minutos.length() == 1) {
            minutos = "0" + minutos;
        }
        if (segundo.length() == 1) {
            segundo = "0" + segundo;
        }
        return (hora + ":" + minutos + ":" + segundo);

    }

    public String FormatoHoras(double tiempo_en_segundos) {
        double ensegundos = tiempo_en_segundos; //tiene 2 decimales
        int minuto = 0, horas = 0, minutofinal = 0, segundos = 0, segundofinal = 0;
        double enminutos = (int) (ensegundos / 60 * 100) / 100.0;
        minuto = (int) enminutos;
        if (minuto >= 60) {
            horas = minuto / 60;
            minutofinal = minuto - (horas * 60);
        }
        else {
            minutofinal = minuto;
        }
        segundos = (int) Math.round((enminutos - minuto) * 60);
        segundofinal = segundos;
        return DevolverFormateado(horas, minutofinal, segundofinal);
    }

    public void GuardarLista(ListaDobleConOrden ldco, String ultima_lista) {
        String Filtroingresado = "";
        JFileChooser FileGuardar = new JFileChooser(ultima_lista);
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);

        int resultado = FileGuardar.showSaveDialog(FileGuardar);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        NodoDoble auxiliar = ldco.getInicio();

        if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos M3U") == 0) {
            Filtroingresado = ".m3u";
        }
        else if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos REP") == 0) {
            Filtroingresado = ".rep";
        }
        try {
            FileOutputStream salida = null;

            if (FileGuardar.getSelectedFile().toString().indexOf(Filtroingresado) != -1) {
                salida = new FileOutputStream(FileGuardar.getSelectedFile());
            }
            else {
                salida = new FileOutputStream(FileGuardar.getSelectedFile() + Filtroingresado);
            }


            BufferedOutputStream memoria = new BufferedOutputStream(salida);
            while (auxiliar != null) {
                Direcciones dir = auxiliar.getNodo();
                Object datos = dir.getDireccion();
                memoria.write((datos.toString() + System.getProperty("line.separator")).getBytes());//Separa por lineas

                //avanza al nodo siguiente
                auxiliar = auxiliar.getApuntSgte();
            }
            memoria.flush();
            salida.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Guardando Archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void CargarLista(String ultima_lista, Tabla tabla) {
        JFileChooser FileGuardar = new JFileChooser(ultima_lista);
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Archivos Soportados", "m3u", "rep");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);
        FileGuardar.setFileFilter(filtro2);
        int resultado = FileGuardar.showOpenDialog(FileGuardar);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        tabla.objeto.Traer_Lista(FileGuardar.getSelectedFile());
        try {
            GUARDAR_ULTIMA_LISTA(FileGuardar.getSelectedFile().getPath());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SiguienteAnterior(int valor, int control) {
        //control ==1 es para aleatorio
        if (abc.repetir_Cancion) {
            REP_pista(Pista);
        }
        else {
            if (IsSiguiente(valor)) {
                if (IsAleatorio(control)) {
                    if (Pista == 0) {
                        REP_pista(Pista);
                    }
                    else {
                        Pista = (int) (Math.random() * (Libreria_Tabla.getMiTabla().getRowCount() - 1));
                        REP_pista(Pista);
                    }
                }
                else {
                    if (Pista == 0) {
                        REP_pista(Pista);
                    }
                    else {
                        Pista = Pista - 1;
                        REP_pista(Pista);
                    }
                }
            }
            else {
                if (IsAleatorio(control)) {
                    if ((Pista + 1) == Libreria_Tabla.getMiTabla().getRowCount()) {
                        //System.out.println("al inicio");
                        //manda el contador al inicio paran emular un regreso
                        Pista = 0;
                        REP_pista(Pista);
                    }
                    else {
                        try {
                            Pista = Pista + 1;
                            REP_pista(Pista);
                        }
                        catch (Exception e) {
                            Reproducir(Pista - 1);
                            Libreria_Tabla.getMiTabla().changeSelection((Pista - 1), 1, false, false);
                            Pista = Pista - 1;
                        }
                    }
                }
                else {
                    try {
                        Pista = (int) (Math.random() * (Libreria_Tabla.getMiTabla().getRowCount() - 1));
                        REP_pista(Pista);
                    }
                    catch (Exception e) {
                        Reproducir(Pista - 1);
                        Libreria_Tabla.getMiTabla().changeSelection((Pista - 1), 1, false, false);
                        Pista = Pista - 1;
                    }
                }
            }
        }
    }

    public void REP_pista(int Pista) {
        Reproducir(Pista);
        Libreria_Tabla.getMiTabla().changeSelection(Pista, 1, false, false);
    }

    private boolean IsSiguiente(int valor) {
        boolean sig = false;
        if (valor == 0) {
            sig = true;
        }
        return sig;
    }

    private boolean IsAleatorio(int control) {
        boolean alea = false;
        if (control == 0) {
            alea = true;
        }
        return alea;
    }

    public void MOSTRARINFO(String nombre, String direccion, JTextField txtInformacion, JTextField Posicion, JTextField txtBit) {
        final double constante = 6.03;
        try {
            if (direccion.contains(".mp3")) {
                mpeg.load(new File(direccion));
                TRANSFORMAR(mpeg, txtInformacion, Posicion, txtBit, nombre, constante);
            }
            else if (direccion.contains(".ogg")) {
                ogg.load(new File(direccion));
                TRANSFORMAR(ogg, txtInformacion, Posicion, txtBit, nombre, constante);
            }
            else if (direccion.contains(".flac")) {
                fla.load(new File(direccion));
                TRANSFORMAR(fla, txtInformacion, Posicion, txtBit, nombre, constante);
            }
            else if (direccion.contains(".ape")) {
                ape.load(new File(direccion));
                TRANSFORMAR(ape, txtInformacion, Posicion, txtBit, nombre, constante);
            }
            else if (direccion.contains(".wav")) {
                wav.load(new File(direccion));
                TRANSFORMAR(wav, txtInformacion, Posicion, txtBit, nombre, constante);
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de IO");
        }
        catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error archivo no soportado");
        }
    }

    public void ModificarVolumen(JSlider Volumen) {
        Volumen.setToolTipText(String.valueOf(Volumen.getValue()));
        try {
            int Valor_ganancia = Volumen.getValue();
            int Maxima_ganancia = Volumen.getMaximum();
            if (Valor_ganancia == 0) {
                ReproductorBasico.setGain(0);
            }
            else {
                ReproductorBasico.setGain(((double) Valor_ganancia / (double) Maxima_ganancia));
            }
            try {
                GUARDAR_VOLUMEN(Volumen.getValue());
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (ReproductorExcepcion ex) {
            //System.out.println("Error");
        }
    }

    public void SeleccionarArchivo() {
        if (Libreria_Tabla.getMiTabla().getRowCount() != 0) {
            int Pista = tabla.Contador_de_celda;
            Reproducir(Pista);//Manda el numero de celda para que reprodusca
        }
    }

    public void BuscarDuplicaddos(ListaDobleConOrden ldco) {
        //Aqui mi algoritmo de buskeda de duplicados
        //no sera lo mejor en buskeda(eficiente) pero funka para el uso
        for (int i = 0; i < Libreria_Tabla.getMiTabla().getRowCount(); i++) {
            for (int j = 0; j < i; j++) {
                if (Libreria_Tabla.getMiTabla().getValueAt(i, 1).toString().compareTo(Libreria_Tabla.getMiTabla().getValueAt(j, 1).toString()) == 0) {
                    //Eliminar
                    NodoDoble auxiliar = ldco.busca(new File(Libreria_Tabla.getMiTabla().getValueAt(i, 1).toString()));
                    if (auxiliar != null) {
                        ldco.elimina(auxiliar);
                    }
                }
            }
        }
        Libreria_Tabla.ActualizaTabla();
    }

    public void Reproducir(int Pista) {
        Object direccion = Libreria_Tabla.getMiTabla().getValueAt(Pista, 1);
        // System.out.println("Pista "+direccion);//Nombre de la celda
        String file = direccion.toString();
        System.out.println("Pistf " + file);
        String nombre1 = Libreria_Tabla.getMiTabla().getValueAt(Pista, 0).toString();
        try {
            loadFile(file);
            reproducir();
            ModificarVolumen(Volumen);
            abc.SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker2.png")));
            MOSTRARINFO(nombre1, file, abc.txtInformacion, abc.Posicion, abc.txtBit);
        }
        catch (ReproductorExcepcion e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Archivo invalido! \nElija otro ", "Error Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String[] CONVERTIR(String[] extencion_archivo) {
        String ob[] = new String[extencion_archivo.length];
        System.out.println(extencion_archivo.length);
        for (int i = 0; i < extencion_archivo.length; i++) {
            ob[i] = extencion_archivo[i].substring(1);
            //System.out.println(ob[i]);
        }
        return ob;
    }

    void GENERAR_FILTRO(JFileChooser archivo) {
        FileNameExtensionFilter[] filtro = new FileNameExtensionFilter[extencion_archivo.length];
        for (int i = 0; i < extencion_archivo.length; i++) {
            filtro[i] = new FileNameExtensionFilter("Archivo " + extencion_archivo[i].substring(1).toUpperCase(), extencion_archivo[i].substring(1));
            archivo.setFileFilter(filtro[i]);
        }
    }

    public void ABRIR(String tipo, String ultima_direccion) {
        JFileChooser archivo = new JFileChooser(ultima_direccion);
        archivo.setMultiSelectionEnabled(true);
        //archivo.setDragEnabled(true);
        archivo.getDragEnabled();
        FileNameExtensionFilter filtro_total = new FileNameExtensionFilter("Audio", CONVERTIR(extencion_archivo));
        GENERAR_FILTRO(archivo);
        archivo.setFileFilter(filtro_total);
        if (tipo.equals("Archivos")) {
            archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int resultado = archivo.showOpenDialog(archivo);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            }
            else {
                try {
                    System.out.println("guardado " + archivo.getSelectedFile().getParent());
                    GUARDAR_ULTIMA_DIRECCION(archivo.getSelectedFile().getParent());
                    System.out.println(archivo.getSelectedFile());
                    tabla.objeto.LlenarTabla(archivo.getSelectedFiles());
                }
                catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error Cargando Archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (tipo.equals("Carpeta")) {
            archivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resultado = archivo.showOpenDialog(archivo);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            }
            try {
                System.out.println("guardadoC " + archivo.getSelectedFile().getPath());
                GUARDAR_ULTIMA_DIRECCION(archivo.getSelectedFile().getPath());
                tabla.objeto.llamar(archivo.getSelectedFile());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println(e);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error Cargando Carpeta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void Acceder_Conf() {
        try {
            canonicalPath = new File(".").getCanonicalPath() + "/conf.txt";
            propiedades_conf.load(new FileInputStream(canonicalPath));
        }
        catch (IOException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No existe fichero se creara uno");
        }
    }

    void CARGAR_CONFIGURACIONES() {
        abc.ultima_direccion = ULTIMA_DIRECCION();
        abc.ultima_lista = ULTIMA_LISTA();
        VOLUMEN(abc.Volumen);
        abc.estado1 = MUTE();
        MODO_PRESENTACION(abc.Modo_Presentacion);
        EXTENCIONES();
    }

    private String ULTIMA_DIRECCION() {
        String ultima_direccion = propiedades_conf.getProperty("ultima_direccion");
        return ultima_direccion;
    }

    private String ULTIMA_LISTA() {
        String ultima_lista = propiedades_conf.getProperty("ultima_lista");
        return ultima_lista;
    }

    private boolean MUTE() {
        boolean estado1 = Boolean.valueOf(propiedades_conf.getProperty("mute"));
        return estado1;
    }

    public void EXTENCIONES() {
        System.out.println(propiedades_conf.getProperty("extenciones"));
        int longitud = propiedades_conf.getProperty("extenciones").split(",").length;
        exten = new String[longitud];
        exten = propiedades_conf.getProperty("extenciones").split(",");
        for (int i = 0; i < longitud; i++) {
            System.out.println(exten[i]);
        }
        extencion_archivo = exten;
    }

    public void VOLUMEN(JSlider Volumen) {
        int valor_volumen = 0;
        valor_volumen = Integer.parseInt(propiedades_conf.getProperty("volumen"));
        Volumen.setValue(valor_volumen);
    }

    private void MODO_PRESENTACION(JCheckBoxMenuItem Modo_Presentacion) {
        Modo_Presentacion.setState(Boolean.valueOf(propiedades_conf.getProperty("modo_presentacion")));
    }

    public void GUARDAR_ULTIMA_LISTA(String Valor) throws FileNotFoundException {
        GUARDAR_LLAVE("ultima_lista", Valor, propiedades_conf, canonicalPath);
    }

    public void GUARDAR_VOLUMEN(int value) throws FileNotFoundException {
        GUARDAR_LLAVE("volumen", String.valueOf(value), propiedades_conf, canonicalPath);
    }

    public void GUARDAR_ULTIMA_DIRECCION(String Valor) throws FileNotFoundException {
        GUARDAR_LLAVE("ultima_direccion", Valor, propiedades_conf, canonicalPath);
    }

    public void GUARDAR_ULTIMO_MUTE(boolean estado1) throws FileNotFoundException {
        GUARDAR_LLAVE("mute", String.valueOf(estado1), propiedades_conf, canonicalPath);
    }

    public void GUARDAR_ULTIMO_MODO(boolean selected) throws FileNotFoundException {
        GUARDAR_LLAVE("modo_presentacion", String.valueOf(selected), propiedades_conf, canonicalPath);
    }

    public void GUARDAR_LLAVE(String llave, String Valor, Properties propiedades_conf, String canonicalPath) throws FileNotFoundException {
        propiedades_conf.setProperty(llave, Valor);
        propiedades_conf.save(new FileOutputStream(canonicalPath), null);
    }

    public void abierto(Map Infoaudio) {
        audioInfo = Infoaudio;
        SpectrumTimeAnalyzer analizer = (SpectrumTimeAnalyzer) Panel_espectro;
        analizer.stopDSP();
        analizer.closeDSP();
        analizer.setupDSP((SourceDataLine) audioInfo.get("basicplayer.sourcedataline"));
        analizer.startDSP((SourceDataLine) audioInfo.get("basicplayer.sourcedataline"));
    }

    public void progreso(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        Procesar_Progreso(bytesread, microseconds, pcmdata, properties);
    }

    public void estadoActualizado(ReproductorEvento event) {
        System.out.println("evento " + event);
    }

    public void setControlador(Controlador controller) {
        ReproductorBasico = (Reproductor) controller;
    }

    public void reproducir() {
        try {
            ReproductorBasico.reproducir();
        }
        catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void parar() {
        try {
            ReproductorBasico.parar();
        }
        catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void pausar() {
        try {
            ReproductorBasico.pausar();
        }
        catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void resumir() {
        try {
            ReproductorBasico.resumir();
        }
        catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public static void loadFile(String ruta) throws ReproductorExcepcion {
        tamañoarchivo = new File(ruta);
        ReproductorBasico.abrir(new File(ruta));
    }

    public static void loadFile(URL direccion) {
        try {
            tamañoarchivo = new File(direccion.toString());
            ReproductorBasico.abrir(direccion);
        }
        catch (ReproductorExcepcion ex) {
            JOptionPane.showMessageDialog(null, "URL nula");
        }
    }

    private void PROGRESARBARRA(long secondsAmount, long total) {
        abc.Progreso_percent.setText(abc.Progreso1.getValue() + "%");
        int posValue = ((int) Math.round(secondsAmount * abc.Progreso1.getMaximum() / total));
        abc.Progreso1.setValue(posValue);

        if (abc.Progreso1.getValue() >= 99 && Modo_Presentacion.isSelected() == false) {
            if (repetir && Pista == (Libreria_Tabla.getMiTabla().getRowCount() - 1)) {
                Reproducir(0);
            }
            else {
                SiguienteAnterior(1, control1);
            }
            abc.Progreso1.setValue(0);
        }
        else if (abc.Progreso1.getValue() >= 10 && Modo_Presentacion.isSelected()) {
            if (repetir && Pista == (Libreria_Tabla.getMiTabla().getRowCount() - 1)) {
                Reproducir(0);
            }
            else {
                SiguienteAnterior(1, control1);
            }
            abc.Progreso1.setValue(0);
        }
    }

    public long getLongitud_tiempo_estimado(Map properties) {
        long milliseconds = -1;
        int byteslength = -1;
        if (properties != null) {
            if (properties.containsKey("audio.length.bytes")) {
                byteslength = ((Integer) properties.get("audio.length.bytes")).intValue();
            }
            if (properties.containsKey("duration")) {
                milliseconds = (int) (((Long) properties.get("duration")).longValue()) / 1000;
            }
            else {
                // Try to compute duration
                int bitspersample = -1;
                int channels = -1;
                float samplerate = -1.0f;
                int framesize = -1;
                if (properties.containsKey("audio.samplesize.bits")) {
                    bitspersample = ((Integer) properties.get("audio.samplesize.bits")).intValue();
                }
                if (properties.containsKey("audio.channels")) {
                    channels = ((Integer) properties.get("audio.channels")).intValue();
                }
                if (properties.containsKey("audio.samplerate.hz")) {
                    samplerate = ((Float) properties.get("audio.samplerate.hz")).floatValue();
                }
                if (properties.containsKey("audio.framesize.bytes")) {
                    framesize = ((Integer) properties.get("audio.framesize.bytes")).intValue();
                }
                if (bitspersample > 0) {
                    milliseconds = (int) (1000.0f * byteslength / (samplerate * channels * (bitspersample / 8)));
                }
                else {
                    milliseconds = (int) (1000.0f * byteslength / (samplerate * framesize));
                }
            }
        }
        return milliseconds;
    }

    private void Procesar_Progreso(int bytes_leidos, long micro_segundos, byte[] pcmdata, Map propiedades) {
        SpectrumTimeAnalyzer analyzer = (SpectrumTimeAnalyzer) Panel_espectro;
        analyzer.writeDSP(pcmdata);
        int byteslength = -1;
        long total = -1;
        // If it fails then try again with JavaSound SPI.
        if (total <= 0) {
            total = (long) Math.round(getLongitud_tiempo_estimado(audioInfo) / 1000);
        }
        // If it fails again then it might be stream => Total = -1
        if (total <= 0) {
            total = -1;
        }
        if (audioInfo.containsKey("audio.length.bytes")) {
            byteslength = ((Integer) audioInfo.get("audio.length.bytes")).intValue();
        }
        float progress = -1.0f;
        if ((bytes_leidos > 0) && ((byteslength > 0))) {
            progress = bytes_leidos * 1.0f / byteslength * 1.0f;
        }
        if (audioInfo.containsKey("audio.type")) {
            String audioformat = (String) audioInfo.get("audio.type");
            if (audioformat.equalsIgnoreCase("mp3")) {
                // Equalizer
                if (propiedades.containsKey("mp3.equalizer")) {
                    equalizar.setBands((float[]) propiedades.get("mp3.equalizer"));
                }
                if (total > 0) {
                    secondsAmount = (long) (total * progress);
                }
                else {
                    secondsAmount = -1;
                }
            }
            else if (audioformat.equalsIgnoreCase("wave")) {
                secondsAmount = (long) (total * progress);
            }
            else {
                secondsAmount = (long) Math.round(micro_segundos / 1000000);
                equalizar.setBands(null);
            }
        }
        else {
            secondsAmount = (long) Math.round(micro_segundos / 1000000);
            equalizar.setBands(null);
        }
        if (total != 0) {
            PROGRESARBARRA(secondsAmount, total);
        }
    }

    public void MOVIDA_MOUSE(JButton SPEAKER, JSlider Progreso1, JSlider Volumen) throws HeadlessException {
        try {
            int cal = (int) (tamañoarchivo.length() * Progreso1.getValue() / 100);
            System.out.println("*** " + cal);
            ReproductorBasico.buscar_saltar(cal);
            if (SPEAKER.isSelected()) {
                ReproductorBasico.setGain(0);
            }
            else {
                ModificarVolumen(Volumen);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No lo movere xq no estoy reproduciendo", "Error", JOptionPane.ERROR_MESSAGE);
            Progreso1.setValue(0);
        }
    }

    public void BOTON_MUTE_N(boolean estado, JButton SPEAKER, JSlider Volumen) {
        if (estado) {
            try {
                SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker3.png")));
                ReproductorBasico.setGain(0);
            }
            catch (ReproductorExcepcion ex) {
            }
            try {
                GUARDAR_ULTIMO_MUTE(estado);
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker2.png")));
            ModificarVolumen(Volumen);
            try {
                GUARDAR_ULTIMO_MUTE(estado);
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void TRANSFORMAR(MpegInfo mpeg, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = mpeg.getMilisegundos();
        String formatohora = FormatoHoras(mpeg.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(mpeg.getBitRate() / 1000 + " Kbps  " + mpeg.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(OggVorbisInfo ogg, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = ogg.getMilisegundos();
        System.out.println("mili " + ogg.getMilisegundos() + " seg " + ogg.getTiempo_en_segundos());
        String formatohora = FormatoHoras(ogg.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(ogg.getBitRate() / 1000 + " Kbps  " + ogg.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(FlacInfo fla, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = fla.getMilisegundos();
        String formatohora = FormatoHoras(fla.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(fla.getBitRate() / 1000 + " Kbps  " + fla.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(APEInfo ape, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = ape.getMilisegundos();
        String formatohora = FormatoHoras(ape.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(ape.getBitRate() / 1000 + " Kbps  " + ape.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(WavInfo wav, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, double constante) {
        duration = wav.getMilisegundos();
        String formatohora = FormatoHoras(wav.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(wav.getBitRate() / 1000 + " Kbps  " + wav.getSamplingRate() / 1000 + " KHZ");
    }
}
