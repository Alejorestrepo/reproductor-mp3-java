package reproductorExpandible;

import java.util.logging.Level;
import java.util.logging.Logger;
import elementos_de_control.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.io.*;
import javazoom.jlgui.player.amp.visual.ui.SpectrumTimeAnalyzer;

/**
 *
 * @author JONATHAN
 */
public class GUIReproductor extends javax.swing.JFrame{
//cargar archivo de configuracion

    Info panel_info;
    Desktop elemento;
    private UIManager.LookAndFeelInfo apariencias[];
    static ListaDobleConOrden ldco = new ListaDobleConOrden();
    boolean estado = false, repetir = false, aleatorio = false, estado1 = false, listado = false, equalizador = false,repetir_Cancion = false;
    static boolean duplicado = false;
    String nombre1;
    public int control1 = 0, veces = 0, tipo;
    static boolean noreproducible = false;
    String ultima_direccion = "C:", ultima_lista = "C:";
    int valor_volumen = 50;
    Libreria metodos_internos;

    /** Creates new form GUIReproductor */
    public GUIReproductor() {
        elemento = Desktop.getDesktop();
        /*Verifica que el ambiente del SO soporte los procedimientos*/
        if (elemento.isDesktopSupported() == false) {
            JOptionPane.showMessageDialog(this, "El sistema no soporta los procedimientos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
        initComponents();
        metodos_internos = new Libreria(this);
        metodos_internos.Acceder_Conf();
        metodos_internos.CARGAR_CONFIGURACIONES(Volumen, ultima_lista, ultima_direccion, estado1, Modo_Presentacion);
        setLocation(400, 200);
        apariencias = UIManager.getInstalledLookAndFeels();
        this.addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e) {
                MinimizarAlReloj();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        Panel_espectro = new SpectrumTimeAnalyzer();
        Progreso_percent = new javax.swing.JLabel();
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
        MenuCancion = new javax.swing.JCheckBoxMenuItem();
        Modo_Presentacion = new javax.swing.JCheckBoxMenuItem();
        MenuAyuda = new javax.swing.JMenu();
        AcercaDE = new javax.swing.JMenuItem();
        Web = new javax.swing.JMenuItem();
        Contactar = new javax.swing.JMenuItem();
        MenuTema = new javax.swing.JMenu();
        TipoWindows = new javax.swing.JCheckBoxMenuItem();
        TipoRedondo = new javax.swing.JCheckBoxMenuItem();
        TipoJava = new javax.swing.JCheckBoxMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setTitle("Reproductor MP3 Java Sistema Palomino");
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

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

        SpectrumTimeAnalyzer analyzer = (SpectrumTimeAnalyzer) Panel_espectro;
        analyzer.setDisplayMode(SpectrumTimeAnalyzer.DISPLAY_MODE_SCOPE);
        analyzer.setPeakColor(Color.red);
        analyzer.setBackground(Color.white);
        analyzer.setSpectrumAnalyserBandCount(19);
        analyzer.setSpectrumAnalyserDecay(0.05f);
        int fps = SpectrumTimeAnalyzer.DEFAULT_FPS;
        analyzer.setFps(fps);
        analyzer.setPeakDelay((int) (fps * SpectrumTimeAnalyzer.DEFAULT_SPECTRUM_ANALYSER_PEAK_DELAY_FPS_RATIO));
        Panel_espectro.setPreferredSize(new java.awt.Dimension(50, 65));

        javax.swing.GroupLayout Panel_espectroLayout = new javax.swing.GroupLayout(Panel_espectro);
        Panel_espectro.setLayout(Panel_espectroLayout);
        Panel_espectroLayout.setHorizontalGroup(
            Panel_espectroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );
        Panel_espectroLayout.setVerticalGroup(
            Panel_espectroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        Progreso_percent.setFont(new java.awt.Font("Tahoma", 1, 14));
        Progreso_percent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Progreso_percent.setText("0%");

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

        MenuCancion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        MenuCancion.setText("Repetir Cancion");
        MenuCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCancionActionPerformed(evt);
            }
        });
        MenuOpciones.add(MenuCancion);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnDetalles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Progreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Progreso_percent, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addComponent(SPEAKER, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Volumen, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Posicion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtBit, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addComponent(btnEqualizador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addComponent(Panel_espectro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Posicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEqualizador, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                .addComponent(btnLista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE))))
                    .addComponent(Panel_espectro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Progreso_percent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Progreso1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuArchivoActionPerformed
        metodos_internos.ABRIR("Archivos", ultima_direccion);
    }//GEN-LAST:event_MenuArchivoActionPerformed

    private void MenuCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCarpetaActionPerformed
        metodos_internos.ABRIR("Carpeta", ultima_direccion);
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
        metodos_internos.BuscarDuplicaddos(ldco);
        //Hace q regrese a la primera opcion
        PermitirDuplicado.setSelected(true);
        EliminarDuplicadoDespues.setSelected(false);
}//GEN-LAST:event_EliminarDuplicadoDespuesActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        Busqueda bcd = new Busqueda(ldco, metodos_internos);
        bcd.show();
}//GEN-LAST:event_BuscarActionPerformed

    private void EliminarElegidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarElegidoActionPerformed
        //System.out.println(tabla.rutaTabla);
        NodoDoble auxiliar = ldco.busca((File) Libreria.tabla.rutaTabla);
        if (auxiliar != null) {
            ldco.elimina(auxiliar);
            Libreria_Tabla.ActualizaTabla();
        }
}//GEN-LAST:event_EliminarElegidoActionPerformed

    private void EliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarTodoActionPerformed
        ldco.setInicio(null);
        Libreria_Tabla.inicializaTabla();
        Habilitar(false);
        metodos_internos.parar();
        Progreso1.setValue(0);
        Posicion.setText("");
        txtInformacion.setText("");
}//GEN-LAST:event_EliminarTodoActionPerformed

    private void CargarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarListaActionPerformed
        metodos_internos.CargarLista(ultima_lista, Libreria.tabla);
        Habilitar(true);
}//GEN-LAST:event_CargarListaActionPerformed

    private void GuardarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarListaActionPerformed
        metodos_internos.GuardarLista(ldco, ultima_lista);
}//GEN-LAST:event_GuardarListaActionPerformed

    private void AcercaDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaDEActionPerformed
        Acerca objeto_panel = new Acerca();
        JOptionPane.showMessageDialog(this, objeto_panel, "Acerca de:", JOptionPane.INFORMATION_MESSAGE);
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
        metodos_internos.ABRIR("Archivos", ultima_direccion);
}//GEN-LAST:event_btnAbrirActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        metodos_internos.SeleccionarArchivo();
}//GEN-LAST:event_btnPlayActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        metodos_internos.parar();
        SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker1.png")));
        metodos_internos.secondsAmount = 0;
        SpectrumTimeAnalyzer analizer = (SpectrumTimeAnalyzer) Panel_espectro;
        analizer.stopDSP();
        analizer.closeDSP();
}//GEN-LAST:event_btnStopActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed

        estado = !estado;
        if (estado) {
            metodos_internos.pausar();
        }
        else {
            metodos_internos.resumir();
        }
}//GEN-LAST:event_btnPausaActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        try {
            metodos_internos.SiguienteAnterior(0, control1);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try {
            metodos_internos.SiguienteAnterior(1, control1);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void SPEAKERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPEAKERActionPerformed
        estado1 = !estado1;
        metodos_internos.BOTON_MUTE_N(estado1, SPEAKER, Volumen);
    }//GEN-LAST:event_SPEAKERActionPerformed

    private void VolumenMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolumenMouseDragged
        metodos_internos.ModificarVolumen(Volumen);
}//GEN-LAST:event_VolumenMouseDragged

    private void Progreso1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Progreso1MouseDragged
        metodos_internos.MOVIDA_MOUSE(SPEAKER, Progreso1, Volumen);
}//GEN-LAST:event_Progreso1MouseDragged

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        listado = !listado;
        if (listado) {
            Libreria.tabla.setVisible(true);
        }
        else {
            Libreria.tabla.setVisible(false);
        }

    }//GEN-LAST:event_btnListaActionPerformed

    private void btnEqualizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualizadorActionPerformed
        equalizador = !equalizador;
        if (equalizador) {
            metodos_internos.equalizar.setVisible(true);
        }
        else {
            metodos_internos.equalizar.setVisible(false);
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
        }
        else {
            CARGAR_DIRECCION_INT(direccionS, direccion);
        }
    }//GEN-LAST:event_menuURLActionPerformed

    private void BtnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDetallesActionPerformed
        try {
            String audioformato = (String) metodos_internos.audioInfo.get("audio.type");
            if (audioformato.equalsIgnoreCase("mp3")) {
                panel_info = new Info(metodos_internos.mpeg);
                panel_info.show();
            }
            else if (audioformato.equalsIgnoreCase("ogg")) {
                panel_info = new Info(metodos_internos.ogg);
                panel_info.show();
            }
            else if (audioformato.equalsIgnoreCase("flac")) {
                panel_info = new Info(metodos_internos.fla);
                panel_info.show();
            }
            else if (audioformato.equalsIgnoreCase("ape")) {
                panel_info = new Info(metodos_internos.ape);
                panel_info.show();
            }
            else if (audioformato.equalsIgnoreCase("wave")) {
                panel_info = new Info(metodos_internos.wav);
                panel_info.show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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
        Libreria_Tabla.BuscarIrreproducibles();
        EliminarIrreAuto.setSelected(true);
        noreproducible = true;
        EliminarIrreAhora.setSelected(false);
        PermitirIrre.setSelected(false);
        JOptionPane.showMessageDialog(null, "Se eliminaron " + Libreria_Tabla.eliminados + " elementos de la lista", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        Libreria_Tabla.eliminados = 0;
    }//GEN-LAST:event_EliminarIrreAhoraActionPerformed

    private void Modo_PresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modo_PresentacionActionPerformed
        // solo reproducir 15 segundos y saltar
        if (!Modo_Presentacion.isSelected()) {
            JOptionPane.showConfirmDialog(null, "De este modo se desactiva reproduccion un 10% del audio", "Aviso", JOptionPane.DEFAULT_OPTION);
            try {
                metodos_internos.GUARDAR_ULTIMO_MODO(Modo_Presentacion.isSelected());
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (Modo_Presentacion.isSelected()) {
            JOptionPane.showConfirmDialog(null, "De este modo solo reproducira un 10% del audio", "Aviso", JOptionPane.DEFAULT_OPTION);
            try {
                metodos_internos.GUARDAR_ULTIMO_MODO(Modo_Presentacion.isSelected());
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_Modo_PresentacionActionPerformed

    private void VolumenStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_VolumenStateChanged
        metodos_internos.ModificarVolumen(Volumen);
    }//GEN-LAST:event_VolumenStateChanged

    private void Menu_CarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_CarpetaActionPerformed
        metodos_internos.ABRIR("Carpeta", ultima_direccion);
    }//GEN-LAST:event_Menu_CarpetaActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        //System.out.println("Lado X "+evt.getPoint().getX()+" Lado Y "+evt.getPoint().getY());

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println((int) d.getWidth() + " otro  " + (int) d.getHeight());
    }//GEN-LAST:event_formMouseMoved

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        evt.getWindow().getX();
        //System.out.println("Lado X " + evt.getWindow().getX());
    }//GEN-LAST:event_formWindowStateChanged

    private void MenuCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCancionActionPerformed
        repetir_Cancion = !repetir_Cancion;
    }//GEN-LAST:event_MenuCancionActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable(){

            public void run() {
                new GUIReproductor().setVisible(true);
            }
        });
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
    private javax.swing.JCheckBoxMenuItem MenuCancion;
    private javax.swing.JMenu MenuLista;
    private javax.swing.JMenuItem MenuOcultar;
    private javax.swing.JMenu MenuOpciones;
    private javax.swing.JMenu MenuPArchivo;
    private javax.swing.JMenu MenuTema;
    private javax.swing.JMenuItem Menu_Carpeta;
    public javax.swing.JCheckBoxMenuItem Modo_Presentacion;
    private javax.swing.JRadioButtonMenuItem NoRepetir;
    public javax.swing.JMenu OpcionLista;
    public javax.swing.JPanel Panel_espectro;
    public javax.swing.JRadioButtonMenuItem PermitirDuplicado;
    private javax.swing.JRadioButtonMenuItem PermitirIrre;
    public javax.swing.JTextField Posicion;
    public javax.swing.JSlider Progreso1;
    public javax.swing.JLabel Progreso_percent;
    private javax.swing.JRadioButtonMenuItem RepetirAleatorio;
    private javax.swing.JRadioButtonMenuItem RepetirLista;
    public javax.swing.JButton SPEAKER;
    private javax.swing.JCheckBoxMenuItem TipoJava;
    private javax.swing.JCheckBoxMenuItem TipoRedondo;
    private javax.swing.JCheckBoxMenuItem TipoWindows;
    public javax.swing.JSlider Volumen;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menuURL;
    public javax.swing.JTextField txtBit;
    public javax.swing.JTextField txtInformacion;
    // End of variables declaration//GEN-END:variables

    public void MinimizarAlReloj() {
        setVisible(false);
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
            ActionListener escuchadorSalir = new ActionListener(){

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

            Reproducir.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    metodos_internos.SeleccionarArchivo();
                }
            });
            Pausar.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    estado = !estado;
                    if (estado) {
                        metodos_internos.pausar();
                    }
                    else {
                        metodos_internos.resumir();
                    }
                }
            });
            Detener.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    metodos_internos.parar();
                }
            });
            PistaAnterior.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    try {
                        metodos_internos.SiguienteAnterior(0, control1);
                    }
                    catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            PistaSiguiente.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    try {
                        metodos_internos.SiguienteAnterior(1, control1);
                    }
                    catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            subirVolumen.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    Volumen.setValue(Volumen.getValue() + 10);
                    metodos_internos.ModificarVolumen(Volumen);
                }
            });
            bajarVolumen.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    Volumen.setValue(Volumen.getValue() - 10);
                    metodos_internos.ModificarVolumen(Volumen);
                }
            });
            mostrar.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                }
            });
            iconoSystemTray.addMouseListener(new MouseAdapter(){

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

            }
            catch (AWTException e) {
                System.err.println("No es posible agregar el icono al System Tray");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Tu sistema no soporta el System Tray :(", "Error Inesperado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void CambiarApariencia(int i) {
        try {
            UIManager.setLookAndFeel(apariencias[i].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Imposible modificar el tema visual", "Lookandfeel inválido.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void Habilitar(boolean b) {
        EliminarElegido.setEnabled(b);
        EliminarTodo.setEnabled(b);
        GuardarLista.setEnabled(b);
    }

    public void Aparecer() {
        try {
            try {
                elemento.browse(new URI("http://jonathan-palomino.blogspot.com/"));
            }
            catch (IOException ex) {
                Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (URISyntaxException ex) {
            JOptionPane.showMessageDialog(null, "Error de web");
        }
    }

    public void Contactar() {

        String email = email = "jhonelfenix@gmail.com";
        String mail = "mailto:" + email;
        try {
            elemento.mail(new URI(mail));
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void CARGAR_DIRECCION_INT(String direccionS, URL direccion) throws HeadlessException {
        try {
            direccion = new URL(direccionS);
        }
        catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Ingreso mal su URL");
        }
        metodos_internos.loadFile(direccion);
        metodos_internos.reproducir();
        metodos_internos.ModificarVolumen(Volumen);
        metodos_internos.MOSTRARINFO(nombre1, direccionS, txtInformacion, Posicion, txtBit);
    }
}
