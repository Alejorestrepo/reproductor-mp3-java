package reproductorExpandible;

import java.util.logging.Level;
import java.util.logging.Logger;
import reproductorExpandible.tags.MpegInfo;
import elementos_de_control.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.Map;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Properties;
import reproductor.*;

/**
 *
 * @author JONATHAN
 */
public class GUIReproductor extends javax.swing.JFrame implements ReproductorLanzador {
//cargar archivo de configuracion

    Properties propiedades_conf = new Properties();
    Info informata;
    MpegInfo atributos;
    Desktop elemento;
    private UIManager.LookAndFeelInfo apariencias[];
    static ListaDobleConOrden ldco = new ListaDobleConOrden();
    JFileChooser archivo;
    static Tabla tabla;
    Equalizador equalizar;
    private long secondsAmount = 0;
    private Map audioInfo = null;
    File[] Elementos;
    static File Directorio, tamañoarchivo;
    private static Reproductor ReproductorBasico;
    boolean estado = false, repetir = false, aleatorio = false, estado1 = false, listado = false, equalizador = false;
    static boolean duplicado = false;
    String nombre1;
    public int Contador_de_celda = 0, Pista = 0, control1 = 0, veces = 0, tipo;
    private double bytesLength;
    Long duration = null;
    int horas = 0, minutofinal = 0, minuto, segundos, segundofinal;
    static boolean noreproducible = false;
    String ultima_direccion = "C:";
    int valor_volumen = 50;
    String canonicalPath;

    /** Creates new form GUIReproductor */
    public GUIReproductor() {
        elemento = Desktop.getDesktop();
        /*Verifica que el ambiente del SO soporte los procedimientos*/
        if (elemento.isDesktopSupported() == false) {
            JOptionPane.showMessageDialog(this, "El sistema no soporta los procedimientos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
        ReproductorBasico = new Reproductor();
        atributos = new MpegInfo();
        equalizar = new Equalizador(this);
        ReproductorBasico.addBasicPlayerListener(this);
        tabla = new Tabla(this);
        initComponents();
        try {
            canonicalPath = new File(".").getCanonicalPath() + "/conf.txt";
            propiedades_conf.load(new FileInputStream(canonicalPath));
        } catch (IOException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No existe fichero se creara uno");
        }
        CARGAR_CONFIGURACIONES();
        setLocation(400, 200);
        apariencias = UIManager.getInstalledLookAndFeels();
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                MinimizarAlReloj();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrir = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnPausa = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        SPEAKER = new javax.swing.JButton();
        Volumen = new javax.swing.JSlider();
        txtInformacion = new javax.swing.JTextField();
        Posicion = new javax.swing.JTextField();
        Progreso1 = new javax.swing.JSlider();
        btnLista = new javax.swing.JButton();
        btnEqualizador = new javax.swing.JButton();
        txtBit = new javax.swing.JTextField();
        BtnDetalles = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuPArchivo = new javax.swing.JMenu();
        MenuArchivo = new javax.swing.JMenuItem();
        Menu_Carpeta = new javax.swing.JMenuItem();
        menuURL = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MenuOcultar = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();
        MenuLista = new javax.swing.JMenu();
        OpcionLista = new javax.swing.JMenu();
        PermitirDuplicado = new javax.swing.JRadioButtonMenuItem();
        EliminarDuplicadoAuto = new javax.swing.JRadioButtonMenuItem();
        EliminarDuplicadoDespues = new javax.swing.JRadioButtonMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        PermitirIrre = new javax.swing.JRadioButtonMenuItem();
        EliminarIrreAuto = new javax.swing.JRadioButtonMenuItem();
        EliminarIrreAhora = new javax.swing.JRadioButtonMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        Buscar = new javax.swing.JMenuItem();
        EliminarElegido = new javax.swing.JMenuItem();
        EliminarTodo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        CargarLista = new javax.swing.JMenuItem();
        GuardarLista = new javax.swing.JMenuItem();
        MenuOpciones = new javax.swing.JMenu();
        RepetirLista = new javax.swing.JRadioButtonMenuItem();
        NoRepetir = new javax.swing.JRadioButtonMenuItem();
        RepetirAleatorio = new javax.swing.JRadioButtonMenuItem();
        Modo_Presentacion = new javax.swing.JCheckBoxMenuItem();
        MenuAyuda = new javax.swing.JMenu();
        AcercaDE = new javax.swing.JMenuItem();
        Web = new javax.swing.JMenuItem();
        Contactar = new javax.swing.JMenuItem();
        MenuTema = new javax.swing.JMenu();
        TipoWindows = new javax.swing.JCheckBoxMenuItem();
        TipoRedondo = new javax.swing.JCheckBoxMenuItem();
        TipoJava = new javax.swing.JCheckBoxMenuItem();

        setTitle("Reproductor MP3 Java Sistema Palomino");
        setResizable(false);

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/1.JPG"))); // NOI18N
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/3.JPG"))); // NOI18N
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/2.JPG"))); // NOI18N
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnPausa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/4.JPG"))); // NOI18N
        btnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausaActionPerformed(evt);
            }
        });

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/5.JPG"))); // NOI18N
        btnAnterior.setToolTipText("atraz");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/6.JPG"))); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        SPEAKER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker1.png"))); // NOI18N
        SPEAKER.setMaximumSize(new java.awt.Dimension(49, 25));
        SPEAKER.setMinimumSize(new java.awt.Dimension(49, 25));
        SPEAKER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPEAKERActionPerformed(evt);
            }
        });

        Volumen.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                VolumenStateChanged(evt);
            }
        });
        Volumen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                VolumenMouseDragged(evt);
            }
        });

        txtInformacion.setEditable(false);
        txtInformacion.setFont(new java.awt.Font("Comic Sans MS", 0, 11));
        txtInformacion.setToolTipText("Informacion de titulo");

        Posicion.setEditable(false);
        Posicion.setToolTipText("Posicion de pista");

        Progreso1.setValue(0);
        Progreso1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Progreso1MouseDragged(evt);
            }
        });

        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/pl1.png"))); // NOI18N
        btnLista.setToolTipText("Lista de Musica");
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });

        btnEqualizador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/eq1.png"))); // NOI18N
        btnEqualizador.setToolTipText("Equalizador");
        btnEqualizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEqualizadorActionPerformed(evt);
            }
        });

        txtBit.setEditable(false);

        BtnDetalles.setText("Mas info");
        BtnDetalles.setToolTipText("Más Informacion");
        BtnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDetallesActionPerformed(evt);
            }
        });

        MenuPArchivo.setText("Archivo");

        MenuArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        MenuArchivo.setText("Añadir Archivo");
        MenuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuArchivoActionPerformed(evt);
            }
        });
        MenuPArchivo.add(MenuArchivo);

        Menu_Carpeta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        Menu_Carpeta.setText("Añadir Carpeta");
        Menu_Carpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_CarpetaActionPerformed(evt);
            }
        });
        MenuPArchivo.add(Menu_Carpeta);

        menuURL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        menuURL.setText("Añadir Direccion");
        menuURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuURLActionPerformed(evt);
            }
        });
        MenuPArchivo.add(menuURL);
        MenuPArchivo.add(jSeparator4);

        MenuOcultar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, 0));
        MenuOcultar.setText("Ocultar Reproductor");
        MenuOcultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOcultarActionPerformed(evt);
            }
        });
        MenuPArchivo.add(MenuOcultar);

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        MenuPArchivo.add(menuSalir);

        jMenuBar1.add(MenuPArchivo);

        MenuLista.setText("Lista");

        OpcionLista.setText("Opciones de Lista");

        PermitirDuplicado.setSelected(true);
        PermitirDuplicado.setText("Permitir Entradas duplicadas");
        PermitirDuplicado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PermitirDuplicadoActionPerformed(evt);
            }
        });
        OpcionLista.add(PermitirDuplicado);

        EliminarDuplicadoAuto.setText("Eliminar Duplicados Automaticamente");
        EliminarDuplicadoAuto.setToolTipText("Eliminara elementos luego de una agregacion");
        EliminarDuplicadoAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarDuplicadoAutoActionPerformed(evt);
            }
        });
        OpcionLista.add(EliminarDuplicadoAuto);

        EliminarDuplicadoDespues.setText("Eliminar Duplicados Ahora");
        EliminarDuplicadoDespues.setToolTipText("Busca y elimina elementos duplicados");
        EliminarDuplicadoDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarDuplicadoDespuesActionPerformed(evt);
            }
        });
        OpcionLista.add(EliminarDuplicadoDespues);
        OpcionLista.add(jSeparator2);

        PermitirIrre.setSelected(true);
        PermitirIrre.setText("Permitir Irreproducibles");
        PermitirIrre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PermitirIrreActionPerformed(evt);
            }
        });
        OpcionLista.add(PermitirIrre);

        EliminarIrreAuto.setText("Eliminar Irreproducibles Automaticamente");
        EliminarIrreAuto.setToolTipText("Funcionara luego de agregar algun elemento");
        EliminarIrreAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarIrreAutoActionPerformed(evt);
            }
        });
        OpcionLista.add(EliminarIrreAuto);

        EliminarIrreAhora.setText("Eliminar Irreproducibles Ahora");
        EliminarIrreAhora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarIrreAhoraActionPerformed(evt);
            }
        });
        OpcionLista.add(EliminarIrreAhora);
        OpcionLista.add(jSeparator3);

        Buscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, 0));
        Buscar.setText("Saltar a Musica");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        OpcionLista.add(Buscar);

        MenuLista.add(OpcionLista);

        EliminarElegido.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        EliminarElegido.setText("Eliminar Seleccionado");
        EliminarElegido.setEnabled(false);
        EliminarElegido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarElegidoActionPerformed(evt);
            }
        });
        MenuLista.add(EliminarElegido);

        EliminarTodo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
        EliminarTodo.setText("Eliminar todo");
        EliminarTodo.setEnabled(false);
        EliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarTodoActionPerformed(evt);
            }
        });
        MenuLista.add(EliminarTodo);
        MenuLista.add(jSeparator1);

        CargarLista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        CargarLista.setText("Cargar Lista");
        CargarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarListaActionPerformed(evt);
            }
        });
        MenuLista.add(CargarLista);

        GuardarLista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        GuardarLista.setText("Guardar Lista");
        GuardarLista.setEnabled(false);
        GuardarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarListaActionPerformed(evt);
            }
        });
        MenuLista.add(GuardarLista);

        jMenuBar1.add(MenuLista);

        MenuOpciones.setText("Opciones");

        RepetirLista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
        RepetirLista.setText("Repetir Lista");
        RepetirLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepetirListaActionPerformed(evt);
            }
        });
        MenuOpciones.add(RepetirLista);

        NoRepetir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        NoRepetir.setSelected(true);
        NoRepetir.setText("No Repetir Lista");
        NoRepetir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRepetirActionPerformed(evt);
            }
        });
        MenuOpciones.add(NoRepetir);

        RepetirAleatorio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        RepetirAleatorio.setText("Repetir Aleatorio");
        RepetirAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepetirAleatorioActionPerformed(evt);
            }
        });
        MenuOpciones.add(RepetirAleatorio);

        Modo_Presentacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        Modo_Presentacion.setText("Modo Presentacion");
        Modo_Presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modo_PresentacionActionPerformed(evt);
            }
        });
        MenuOpciones.add(Modo_Presentacion);

        jMenuBar1.add(MenuOpciones);

        MenuAyuda.setText("Ayuda");

        AcercaDE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        AcercaDE.setText("Acerca de:");
        AcercaDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaDEActionPerformed(evt);
            }
        });
        MenuAyuda.add(AcercaDE);

        Web.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        Web.setText("Web");
        Web.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WebActionPerformed(evt);
            }
        });
        MenuAyuda.add(Web);

        Contactar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        Contactar.setText("Contactar");
        Contactar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactarActionPerformed(evt);
            }
        });
        MenuAyuda.add(Contactar);

        MenuTema.setText("Tema Visual");

        TipoWindows.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, 0));
        TipoWindows.setText("Tipo Windows");
        TipoWindows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoWindowsActionPerformed(evt);
            }
        });
        MenuTema.add(TipoWindows);

        TipoRedondo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, 0));
        TipoRedondo.setText("Tipo Redondeado suave");
        TipoRedondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoRedondoActionPerformed(evt);
            }
        });
        MenuTema.add(TipoRedondo);

        TipoJava.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, 0));
        TipoJava.setSelected(true);
        TipoJava.setText("Tipo Java");
        TipoJava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoJavaActionPerformed(evt);
            }
        });
        MenuTema.add(TipoJava);

        MenuAyuda.add(MenuTema);

        jMenuBar1.add(MenuAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Progreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPausa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(SPEAKER, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Volumen, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Posicion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBit, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEqualizador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Volumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(btnAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPausa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SPEAKER, 0, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(txtInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Posicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEqualizador, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(btnLista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Progreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuArchivoActionPerformed
        ABRIR("Archivos");
    }//GEN-LAST:event_MenuArchivoActionPerformed

    private void MenuCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCarpetaActionPerformed
        ABRIR("Carpeta");
}//GEN-LAST:event_MenuCarpetaActionPerformed

    private void MenuOcultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOcultarActionPerformed
        MinimizarAlReloj();
}//GEN-LAST:event_MenuOcultarActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        System.exit(0);
}//GEN-LAST:event_menuSalirActionPerformed

    private void PermitirDuplicadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PermitirDuplicadoActionPerformed
        duplicado = false;
        EliminarDuplicadoAuto.setSelected(false);
        EliminarDuplicadoDespues.setSelected(false);
}//GEN-LAST:event_PermitirDuplicadoActionPerformed

    private void EliminarDuplicadoAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarDuplicadoAutoActionPerformed
        duplicado = true;
        PermitirDuplicado.setSelected(false);
        EliminarDuplicadoDespues.setSelected(false);
}//GEN-LAST:event_EliminarDuplicadoAutoActionPerformed

    private void EliminarDuplicadoDespuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarDuplicadoDespuesActionPerformed
        PermitirDuplicado.setSelected(false);
        EliminarDuplicadoAuto.setSelected(false);
        BuscarDuplicaddos();
        //Hace q regrese a la primera opcion
        PermitirDuplicado.setSelected(true);
        EliminarDuplicadoDespues.setSelected(false);
}//GEN-LAST:event_EliminarDuplicadoDespuesActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        Busqueda bcd = new Busqueda(ldco, this, tabla);
        bcd.show();
}//GEN-LAST:event_BuscarActionPerformed

    private void EliminarElegidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarElegidoActionPerformed
        System.out.println(tabla.rutaTabla);
        NodoDoble auxiliar = ldco.busca((File) tabla.rutaTabla);
        if (auxiliar != null) {
            ldco.elimina(auxiliar);
            tabla.ActualizaTabla();
        }
}//GEN-LAST:event_EliminarElegidoActionPerformed

    private void EliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarTodoActionPerformed
        ldco.setInicio(null);
        tabla.inicializaTabla();
        Habilitar(false);
        stop();
        Progreso1.setValue(0);
        Posicion.setText("");
        txtInformacion.setText("");
}//GEN-LAST:event_EliminarTodoActionPerformed

    private void CargarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarListaActionPerformed
        CargarLista();
        Habilitar(true);
}//GEN-LAST:event_CargarListaActionPerformed

    private void GuardarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarListaActionPerformed
        GuardarLista();
}//GEN-LAST:event_GuardarListaActionPerformed

    private void AcercaDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaDEActionPerformed
        AcercaDE abc = new AcercaDE();
        abc.show();
}//GEN-LAST:event_AcercaDEActionPerformed

    private void WebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WebActionPerformed
        Aparecer();
}//GEN-LAST:event_WebActionPerformed

    private void ContactarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactarActionPerformed
        Contactar();
}//GEN-LAST:event_ContactarActionPerformed

    private void TipoWindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoWindowsActionPerformed
        TipoRedondo.setSelected(false);
        TipoJava.setSelected(false);
        tipo = 3;
        CambiarApariencia(tipo);
}//GEN-LAST:event_TipoWindowsActionPerformed

    private void TipoRedondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoRedondoActionPerformed
        TipoWindows.setSelected(false);
        TipoJava.setSelected(false);
        tipo = 1;
        CambiarApariencia(tipo);
}//GEN-LAST:event_TipoRedondoActionPerformed

    private void TipoJavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoJavaActionPerformed
        TipoRedondo.setSelected(false);
        TipoWindows.setSelected(false);
        tipo = 0;
        CambiarApariencia(tipo);
}//GEN-LAST:event_TipoJavaActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        ABRIR("Archivos");
}//GEN-LAST:event_btnAbrirActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        SeleccionarArchivo();
}//GEN-LAST:event_btnPlayActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        stop();
        SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker1.png")));
        secondsAmount = 0;
}//GEN-LAST:event_btnStopActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed

        estado = !estado;
        if (estado) {
            pause();
        } else {
            resume();
        }
}//GEN-LAST:event_btnPausaActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        try {
            SiguienteAnterior(0, control1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try {
            SiguienteAnterior(1, control1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void SPEAKERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPEAKERActionPerformed
        estado1 = !estado1;
        if (estado1) {
            try {
                SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker3.png")));
                ReproductorBasico.setGain(0);
            } catch (ReproductorExcepcion ex) {
            }
            try {
                GUARDAR_ULTIMO_MUTE(estado1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker2.png")));
            ModificarVolumen();
            try {
                GUARDAR_ULTIMO_MUTE(estado1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SPEAKERActionPerformed

    private void VolumenMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolumenMouseDragged
        ModificarVolumen();
}//GEN-LAST:event_VolumenMouseDragged

    private void Progreso1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Progreso1MouseDragged
        try {
            int cal = (int) (tamañoarchivo.length() * Progreso1.getValue() / 100);
            ReproductorBasico.buscar_saltar(cal);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No lo movere xq no estoy reproduciendo", "Error", JOptionPane.ERROR_MESSAGE);
            Progreso1.setValue(0);
        }
}//GEN-LAST:event_Progreso1MouseDragged

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        listado = !listado;
        if (listado) {
            tabla.setVisible(true);
        } else {
            tabla.setVisible(false);
        }

    }//GEN-LAST:event_btnListaActionPerformed

    private void btnEqualizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualizadorActionPerformed
        equalizador = !equalizador;
        if (equalizador) {
            equalizar.setVisible(true);
        } else {
            equalizar.setVisible(false);
        }
    }//GEN-LAST:event_btnEqualizadorActionPerformed

    private void RepetirAleatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepetirAleatorioActionPerformed
        NoRepetir.setSelected(false);
        RepetirLista.setSelected(false);
        aleatorio = true;
        repetir = false;
        control1 = 1;
}//GEN-LAST:event_RepetirAleatorioActionPerformed

    private void NoRepetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRepetirActionPerformed
        RepetirAleatorio.setSelected(false);
        RepetirLista.setSelected(false);
        repetir = false;
        aleatorio = false;
        control1 = 0;
}//GEN-LAST:event_NoRepetirActionPerformed

    private void RepetirListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepetirListaActionPerformed
        NoRepetir.setSelected(false);
        RepetirAleatorio.setSelected(false);
        repetir = true;
        aleatorio = false;
        control1 = 0;
}//GEN-LAST:event_RepetirListaActionPerformed

    private void menuURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuURLActionPerformed
        URL direccion = null;
        String direccionS = JOptionPane.showInputDialog(null, "Ingrese aqui la URL a reproducir" + "\n" + "Por ejemplo www.musica.com/archivo.mp3", "Abrir URL", JOptionPane.NO_OPTION);
        int desicion = JOptionPane.showOptionDialog(null, "Desea reproducirlo o agregarlo", "Elija", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (desicion == 1) {
            //agregar
        } else {

            try {
                direccion = new URL(direccionS);
            } catch (MalformedURLException ex) {
                JOptionPane.showMessageDialog(null, "Ingreso mal su URL");
            }
            try {
                loadFile(direccion);
                play();
                ModificarVolumen();
                MOSTRARINFO(direccionS, direccionS);
            } catch (ReproductorExcepcion ex) {
                JOptionPane.showMessageDialog(null, "No se puede reproducir");
            }
        }
    }//GEN-LAST:event_menuURLActionPerformed

    private void BtnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDetallesActionPerformed
        try {
            informata = new Info(atributos);
            informata.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Reprodusca primero el archivo por favor", "Error de Usuario", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnDetallesActionPerformed

    private void PermitirIrreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PermitirIrreActionPerformed
        noreproducible = false;
        EliminarIrreAuto.setSelected(false);
        EliminarIrreAhora.setSelected(false);
    }//GEN-LAST:event_PermitirIrreActionPerformed

    private void EliminarIrreAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarIrreAutoActionPerformed
        noreproducible = true;
        EliminarIrreAhora.setSelected(false);
        PermitirIrre.setSelected(false);
    }//GEN-LAST:event_EliminarIrreAutoActionPerformed

    private void EliminarIrreAhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarIrreAhoraActionPerformed
        Tabla.BuscarIrreproducibles();
        EliminarIrreAuto.setSelected(true);
        noreproducible = true;
        EliminarIrreAhora.setSelected(false);
        PermitirIrre.setSelected(false);
        JOptionPane.showMessageDialog(null, "Se eliminaron " + Tabla.eliminados + " elementos de la lista", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        Tabla.eliminados = 0;
    }//GEN-LAST:event_EliminarIrreAhoraActionPerformed

    private void Modo_PresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modo_PresentacionActionPerformed
        // solo reproducir 15 segundos y saltar
        if (!Modo_Presentacion.isSelected()) {
            JOptionPane.showConfirmDialog(null, "De este modo se desactiva reproduccion un 10% del audio", "Aviso", JOptionPane.DEFAULT_OPTION);
            try {
                GUARDAR_ULTIMO_MODO(Modo_Presentacion.isSelected());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Modo_Presentacion.isSelected()) {
            JOptionPane.showConfirmDialog(null, "De este modo solo reproducira un 10% del audio", "Aviso", JOptionPane.DEFAULT_OPTION);
            try {
                GUARDAR_ULTIMO_MODO(Modo_Presentacion.isSelected());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_Modo_PresentacionActionPerformed

    private void VolumenStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_VolumenStateChanged
        ModificarVolumen();
    }//GEN-LAST:event_VolumenStateChanged

    private void Menu_CarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_CarpetaActionPerformed
        ABRIR("Carpeta");
    }//GEN-LAST:event_Menu_CarpetaActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GUIReproductor().setVisible(true);
            }
        });
    }

    public void abierto(Object arg0, Map arg1) {
        if (arg1.containsKey("audio.length.bytes")) {
            bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
        }
        audioInfo = arg1;
    }

    public void progreso(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        processProgress(bytesread, microseconds, pcmdata, properties);

    }

    public void estadoActualizado(ReproductorEvento event) {
        System.out.println("evento " + event);
    }

    public void setControlador(Controlador controller) {
        ReproductorBasico = (Reproductor) controller;
    }

    public void play() {
        try {
            ReproductorBasico.reproducir();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void stop() {
        try {
            ReproductorBasico.parar();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void pause() {
        try {
            ReproductorBasico.pausar();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void resume() {
        try {
            ReproductorBasico.resumir();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public static void loadFile(String ruta) throws ReproductorExcepcion {
        tamañoarchivo = new File(ruta);
        ReproductorBasico.abrir(new File(ruta));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AcercaDE;
    private javax.swing.JButton BtnDetalles;
    public javax.swing.JMenuItem Buscar;
    public static javax.swing.JMenuItem CargarLista;
    private javax.swing.JMenuItem Contactar;
    public javax.swing.JRadioButtonMenuItem EliminarDuplicadoAuto;
    public javax.swing.JRadioButtonMenuItem EliminarDuplicadoDespues;
    public static javax.swing.JMenuItem EliminarElegido;
    private javax.swing.JRadioButtonMenuItem EliminarIrreAhora;
    private javax.swing.JRadioButtonMenuItem EliminarIrreAuto;
    public static javax.swing.JMenuItem EliminarTodo;
    public static javax.swing.JMenuItem GuardarLista;
    private javax.swing.JMenuItem MenuArchivo;
    private javax.swing.JMenu MenuAyuda;
    private javax.swing.JMenu MenuLista;
    private javax.swing.JMenuItem MenuOcultar;
    private javax.swing.JMenu MenuOpciones;
    private javax.swing.JMenu MenuPArchivo;
    private javax.swing.JMenu MenuTema;
    private javax.swing.JMenuItem Menu_Carpeta;
    private javax.swing.JCheckBoxMenuItem Modo_Presentacion;
    private javax.swing.JRadioButtonMenuItem NoRepetir;
    public javax.swing.JMenu OpcionLista;
    public javax.swing.JRadioButtonMenuItem PermitirDuplicado;
    private javax.swing.JRadioButtonMenuItem PermitirIrre;
    private javax.swing.JTextField Posicion;
    private javax.swing.JSlider Progreso1;
    private javax.swing.JRadioButtonMenuItem RepetirAleatorio;
    private javax.swing.JRadioButtonMenuItem RepetirLista;
    private javax.swing.JButton SPEAKER;
    private javax.swing.JCheckBoxMenuItem TipoJava;
    private javax.swing.JCheckBoxMenuItem TipoRedondo;
    private javax.swing.JCheckBoxMenuItem TipoWindows;
    private javax.swing.JSlider Volumen;
    private javax.swing.JMenuItem Web;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnEqualizador;
    private javax.swing.JButton btnLista;
    private javax.swing.JButton btnPausa;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnStop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menuURL;
    private javax.swing.JTextField txtBit;
    private javax.swing.JTextField txtInformacion;
    // End of variables declaration//GEN-END:variables

    public void ABRIR(String tipo) {
        ULTIMA_DIRECCION();
        archivo = new JFileChooser(ultima_direccion);
        archivo.setMultiSelectionEnabled(true);
        //archivo.setDragEnabled(true);
        archivo.getDragEnabled();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio", "mp3", "wav", "ogg", "flac");
        archivo.setFileFilter(filter);
        if (tipo.equals("Archivos")) {
            archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int resultado = archivo.showOpenDialog(this);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            } else {
                try {
                    GUARDAR_ULTIMA_DIRECCION(archivo.getSelectedFile().getParent());
                    Elementos = archivo.getSelectedFiles();
                    tabla.LlenarTabla(Elementos);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error Cargando Archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (tipo.equals("Carpeta")) {
            archivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resultado = archivo.showOpenDialog(this);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            }
            try {
                GUARDAR_ULTIMA_DIRECCION(archivo.getSelectedFile().getPath());
                Directorio = archivo.getSelectedFile();
                tabla.llamar(Directorio);

            } catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println(e);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error Cargando Carpeta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void MinimizarAlReloj() {

        try {
            setVisible(false);
            GUARDAR_MODO_Oculto(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
        veces += 1;
        //dispose();
        //se declara el objeto tipo icono
        final TrayIcon iconoSystemTray;
        //se verifica que el SystemTray sea soportado
        if (SystemTray.isSupported()) {
            //se obtiene una instancia estática de la clase SystemTray
            SystemTray tray = SystemTray.getSystemTray();
            //esta es la imagen de icono
            ImageIcon icono = new ImageIcon(GUIReproductor.class.getResource("/reproductordesonido/iconos/Reproductor.png"));
            iconoSystemTray = new TrayIcon(icono.getImage(), "Reproductor Java", null);

            iconoSystemTray.setImageAutoSize(true);
            //este listener se asociara con un item del menu contextual
            //que aparece al hacer click derecho sobre el icono
            //este listener se asociara con un item del menu contextual
            //que aparece al hacer click derecho sobre el icono
            ActionListener escuchadorSalir = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //System.out.println("Saliendo...");
                    System.exit(0);
                }
            };
            //menu que aparece al hacer click derecho
            final JPopupMenu popup = new JPopupMenu();
            JMenuItem mostrar = new JMenuItem("Mostrar Reproductor", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/icono.png")));
            JMenuItem subirVolumen = new JMenuItem("subirVolumen +");
            JMenuItem bajarVolumen = new JMenuItem("bajarVolumen -");
            JMenuItem PistaAnterior = new JMenuItem("PistaAnterior", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/5.JPG")));
            JMenuItem PistaSiguiente = new JMenuItem("PistaSiguiente", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/6.JPG")));
            JMenuItem Pausar = new JMenuItem("Pausar", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/4.JPG")));
            JMenuItem Detener = new JMenuItem("Detener", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/2.JPG")));
            JMenuItem Reproducir = new JMenuItem("Reproducir", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/3.JPG")));
            JMenuItem salir = new JMenuItem("Salir", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/salir.png")));
            salir.addActionListener(escuchadorSalir);
            //aniadir un menu con icono (swing)
            popup.add(Reproducir);
            popup.add(Pausar);
            popup.add(Detener);
            popup.addSeparator();
            popup.add(PistaAnterior);
            popup.add(PistaSiguiente);
            popup.addSeparator();
            popup.add(subirVolumen);
            popup.add(bajarVolumen);
            popup.addSeparator();
            popup.add(mostrar);
            popup.add(salir);

            Reproducir.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    SeleccionarArchivo();
                }
            });
            Pausar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    estado = !estado;
                    if (estado) {
                        pause();
                    } else {
                        resume();
                    }
                }
            });
            Detener.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    stop();
                }
            });
            PistaAnterior.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        SiguienteAnterior(0, control1);
                    } catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            PistaSiguiente.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        SiguienteAnterior(1, control1);
                    } catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            subirVolumen.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Volumen.setValue(Volumen.getValue() + 10);
                    ModificarVolumen();
                }
            });
            bajarVolumen.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Volumen.setValue(Volumen.getValue() - 10);
                    ModificarVolumen();
                }
            });
            mostrar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    try {
                        GUARDAR_MODO_Oculto(false);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            iconoSystemTray.addMouseListener(new MouseAdapter() {

                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popup.setLocation(e.getX(), e.getY() - 50);
                        popup.setInvoker(popup);
                        popup.setVisible(true);
                    }
                }
            });
            //se debe capturar una excepción en caso que falle la adicion de un icono
            try {
                if (veces <= 1)//Verificar q solo se agrega una ves un tray icon
                {
                    tray.add(iconoSystemTray);
                }

            } catch (AWTException e) {
                System.err.println("No es posible agregar el icono al System Tray");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tu sistema no soporta el System Tray :(", "Error Inesperado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void CargarLista() {
        JFileChooser FileGuardar = new JFileChooser("C:\\");
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Archivos Soportados", "m3u", "rep");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);
        FileGuardar.setFileFilter(filtro2);
        int resultado = FileGuardar.showOpenDialog(this);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        tabla.Traer_Lista(FileGuardar.getSelectedFile());

    }

    public void GuardarLista() {
        String Filtroingresado = "";
        JFileChooser FileGuardar = new JFileChooser("C:\\");
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);

        int resultado = FileGuardar.showSaveDialog(this);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        NodoDoble auxiliar = ldco.getInicio();

        if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos M3U") == 0) {
            Filtroingresado = ".m3u";
        } else if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos REP") == 0) {
            Filtroingresado = ".rep";
        }
        try {
            FileOutputStream salida = null;

            if (FileGuardar.getSelectedFile().toString().indexOf(Filtroingresado) != -1) {
                salida = new FileOutputStream(FileGuardar.getSelectedFile());
            } else {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Guardando Archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void CambiarApariencia(int i) {
        try {
            UIManager.setLookAndFeel(apariencias[i].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Imposible modificar el tema visual", "Lookandfeel inválido.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void Habilitar(boolean b) {
        EliminarElegido.setEnabled(b);
        EliminarTodo.setEnabled(b);
        GuardarLista.setEnabled(b);
    }

    public void MOSTRARINFO(String nombre, String direccion) {
        final double constante = 6.03;
        try {
            atributos.load(new File(direccion));
            duration = atributos.getMilisegundos();
            String formatohora = FormatoHoras(atributos.getTiempo_en_segundos());
            txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
            txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
            Posicion.setText((Pista + 1) + " de " + tabla.getMiTabla().getRowCount());
            txtBit.setText(atributos.getBitRate() / 1000 + " Kbps  " + atributos.getSamplingRate() / 1000 + " KHZ");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de IO");
        } catch (UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "Error archivo no soportado");
        }
    }

    public void Aparecer() {
        try {
            elemento.browse(new URI("http://jonathan-palomino.blogspot.com/"));

        } catch (URISyntaxException ex) {
            JOptionPane.showMessageDialog(null, "Error de web");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de IO");
        }

    }

    public void BuscarDuplicaddos() {
        for (int i = 0; i < tabla.getMiTabla().getRowCount(); i++) {
            for (int j = 0; j < i; j++) {
                if (tabla.getMiTabla().getValueAt(i, 1).toString().compareTo(tabla.getMiTabla().getValueAt(j, 1).toString()) == 0) {
                    //Eliminar
                    NodoDoble auxiliar = ldco.busca(new File(tabla.getMiTabla().getValueAt(i, 1).toString()));
                    if (auxiliar != null) {
                        ldco.elimina(auxiliar);
                    }
                }
            }
        }
        tabla.ActualizaTabla();
    }

    private void Contactar() {
        String email = JOptionPane.showInputDialog("Ingrese su emal");
        if (email.contains("@")) {
            String mail = "mailto:" + email;
            try {
                elemento.mail(new URI(mail));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error de usuario  Ingrese el @", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void SeleccionarArchivo() {
        if (tabla.getMiTabla().getRowCount() != 0) {
            Pista = tabla.Contador_de_celda;
            Reproducir(tabla.Contador_de_celda);//Manda el numero de celda para que reprodusca
        }

    }

    public void ProgresarBarra(File tamañoarchivo, int progreso) {
        int tamano = (int) tamañoarchivo.length();
        try {
            int valor1 = progreso * 100 / tamano;
            Progreso1.setValue(valor1);
        } catch (Exception w) {
        }
        if (Progreso1.getValue() >= 99 && Modo_Presentacion.isSelected() == false) {
            if (repetir && Pista == (tabla.getMiTabla().getRowCount() - 1)) {
                Reproducir(0);
            } else {
                SiguienteAnterior(1, control1);
            }
            Progreso1.setValue(0);
        } else if (Progreso1.getValue() >= 10 && Modo_Presentacion.isSelected()) {
            if (repetir && Pista == (tabla.getMiTabla().getRowCount() - 1)) {
                Reproducir(0);
            } else {
                SiguienteAnterior(1, control1);
            }
            Progreso1.setValue(0);
        }
    }

    public void Reproducir(int Pista) {
        Object direccion = tabla.getMiTabla().getValueAt(Pista, 1);
        // System.out.println("Pista "+direccion);//Nombre de la celda
        String file = direccion.toString();
        nombre1 = tabla.getMiTabla().getValueAt(Pista, 0).toString();
        try {
            loadFile(file);
            play();
            ModificarVolumen();
            SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker2.png")));
            MOSTRARINFO(nombre1, file);

        } catch (ReproductorExcepcion e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de Archivo! \nElija otro ", "Error Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ModificarVolumen() {
        Volumen.setToolTipText(String.valueOf(Volumen.getValue()));
        try {
            int Valor_ganancia = Volumen.getValue();
            int Maxima_ganancia = Volumen.getMaximum();
            if (Valor_ganancia == 0) {
                ReproductorBasico.setGain(0);
            } else {
                ReproductorBasico.setGain(((double) Valor_ganancia / (double) Maxima_ganancia));
            }
            try {
                GUARDAR_VOLUMEN(Volumen.getValue());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ReproductorExcepcion ex) {
            //System.out.println("Error");
        }
    }

    public void SiguienteAnterior(int valor, int control) {
        //control ==1 es para aleatorio
        if (valor == 0 && control == 1) /// 0 es anterior
        {
            if (Pista == 0) {
                Reproducir(Pista);
                tabla.getMiTabla().changeSelection(Pista, 1, false, false);
            } else {
                Pista = (int) (Math.random() * (tabla.getMiTabla().getRowCount() - 1));
                Reproducir(Pista);
                tabla.getMiTabla().changeSelection(Pista, 1, false, false);
            }
        }
        if (valor == 0 && control == 0) /// 0 es anterior
        {
            if (Pista == 0) {
                Reproducir(Pista);
                tabla.getMiTabla().changeSelection(Pista, 1, false, false);
            } else {
                Pista = Pista - 1;
                Reproducir(Pista);
                tabla.getMiTabla().changeSelection(Pista, 1, false, false);
            }
        }
        if (valor == 1 && control == 0) /// 1 es posterior
        {
            if ((Pista + 1) == tabla.getMiTabla().getRowCount()) {
                System.out.println("al inicio");
                //manda el contador al inicio paran emular un regreso
                Pista = 0;
                Reproducir(Pista);
                tabla.getMiTabla().changeSelection(Pista, 1, false, false);
            } else {
                try {
                    Pista = Pista + 1;
                    Reproducir(Pista);
                    tabla.getMiTabla().changeSelection(Pista, 1, false, false);
                } catch (Exception e) {
                    Reproducir(Pista - 1);
                    tabla.getMiTabla().changeSelection((Pista - 1), 1, false, false);
                    Pista = Pista - 1;
                }
            }
        }
        if (valor == 1 && control == 1) /// 1 es posterior
        {
            try {
                Pista = (int) (Math.random() * (tabla.getMiTabla().getRowCount() - 1));
                Reproducir(Pista);
                tabla.getMiTabla().changeSelection(Pista, 1, false, false);
            } catch (Exception e) {
                Reproducir(Pista - 1);
                tabla.getMiTabla().changeSelection((Pista - 1), 1, false, false);
                Pista = Pista - 1;
            }
        }
    }

    private void processProgress(int bytes_leidos, long micro_segundos, byte[] pcmdata, Map properties) {
        float progressUpdate = (float) (bytes_leidos * 1.0f / bytesLength * 1.0f);
        int progressNow = (int) (bytesLength * progressUpdate);
        ProgresarBarra(tamañoarchivo, progressNow);
        int byteslength = -1;
        long total = -1;
        if (total <= 0) {
            total = (long) Math.round(duration / 1000);
        }
        // If it fails again then it might be stream => Total = -1
        if (total <= 0) {
            total = -1;
        }
        if (audioInfo.containsKey("basicplayer.sourcedataline")) {
            // Spectrum/time analyzer para el analizador de espectro
            //if (ui.getAcAnalyzer() != null) ui.getAcAnalyzer().writeDSP(pcmdata);
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
                // EqualizerUI
                if (properties.containsKey("mp3.equalizer")) {
                    equalizar.setBands((float[]) properties.get("mp3.equalizer"));
                }
                if (total > 0) {
                    secondsAmount = (long) (total * progress);
                } else {
                    secondsAmount = -1;
                }
            } else if (audioformat.equalsIgnoreCase("wave")) {
                secondsAmount = (long) (total * progress);
            } else {
                secondsAmount = (long) Math.round(micro_segundos / 1000000);
                equalizar.setBands(null);
            }
        } else {
            secondsAmount = (long) Math.round(micro_segundos / 1000000);
            equalizar.setBands(null);
        }
    }

    private void loadFile(URL direccion) throws ReproductorExcepcion {
        tamañoarchivo = new File(direccion.toString());
        ReproductorBasico.abrir(direccion);
    }

    private String FormatoHoras(double tiempo_en_segundos) {
        double ensegundos = tiempo_en_segundos; //tiene 2 decimales
        double enminutos = (int) (ensegundos / 60 * 100) / 100.0;
        minuto = (int) enminutos;
        if (minuto >= 60) {
            horas = minuto / 60;
            minutofinal = minuto - (horas * 60);
        } else {
            minutofinal = minuto;
        }
        segundos = (int) Math.round((enminutos - minuto) * 60);
        segundofinal = segundos;
        return DevolverFormateado(horas, minutofinal, segundofinal);
    }

    private String DevolverFormateado(int horas, int minutofinal, int segundofinal) {
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

    private void CARGAR_CONFIGURACIONES() {
        ULTIMA_DIRECCION();
        VOLUMEN();
        MUTE();
        MODO_PRESENTACION();
        MODO_OCULTO();
    }

    private void ULTIMA_DIRECCION() {
        ultima_direccion = propiedades_conf.getProperty("ultima_direccion");
    }

    private void VOLUMEN() {
        valor_volumen = Integer.parseInt(propiedades_conf.getProperty("volumen"));
        Volumen.setValue(valor_volumen);
    }

    private void MUTE() {
        estado1 = Boolean.valueOf(propiedades_conf.getProperty("mute"));
    }

    private void MODO_PRESENTACION() {
        Modo_Presentacion.setState(Boolean.valueOf(propiedades_conf.getProperty("modo_presentacion")));
    }

    public void GUARDAR_VOLUMEN(int value) throws FileNotFoundException {
        System.out.println(String.valueOf(value));
        GUARDAR_LLAVE("volumen", String.valueOf(value));
    }

    public void GUARDAR_ULTIMA_DIRECCION(String Valor) throws FileNotFoundException {
        GUARDAR_LLAVE("ultima_direccion", Valor);
    }

    public void GUARDAR_LLAVE(String llave, String Valor) throws FileNotFoundException {
        propiedades_conf.setProperty(llave, Valor);
        propiedades_conf.save(new FileOutputStream(canonicalPath), null);
    }

    public void GUARDAR_ULTIMO_MUTE(boolean estado1) throws FileNotFoundException {
        GUARDAR_LLAVE("mute", String.valueOf(estado1));
    }

    public void GUARDAR_ULTIMO_MODO(boolean selected) throws FileNotFoundException {
        GUARDAR_LLAVE("modo_presentacion", String.valueOf(selected));
    }

    private void MODO_OCULTO() {
        boolean modo_oculto = (Boolean.valueOf(propiedades_conf.getProperty("modo_oculto")));
        System.out.println(modo_oculto);
        if (modo_oculto) {
            MinimizarAlReloj();
        }
    }

    public void GUARDAR_MODO_Oculto(boolean selected) throws FileNotFoundException {
        GUARDAR_LLAVE("modo_oculto", String.valueOf(selected));
        System.out.println(selected);
    }
}
