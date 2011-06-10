package reproductorExpandible;

import reproductorExpandible.tags.APEInfo;
import reproductorExpandible.tags.FlacInfo;
import reproductorExpandible.tags.MpegInfo;
import reproductorExpandible.tags.OggVorbisInfo;
import reproductorExpandible.tags.WavInfo;

/**
 *
 * @author JONATHAN
 */
public class Info extends javax.swing.JPanel{

    /** Creates new form Info */
    Info(MpegInfo atributos) {
        setLocation(300, 400);
        initComponents();
        MOSTRAR(atributos);
    }

    Info(OggVorbisInfo atributos) {
        setLocation(300, 400);
        initComponents();
        MOSTRAR(atributos);
    }

    Info(FlacInfo atributos) {
        setLocation(300, 400);
        initComponents();
        MOSTRAR(atributos);
    }

    Info(APEInfo atributos) {
        setLocation(300, 400);
        initComponents();
        MOSTRAR(atributos);
    }

    public Info(WavInfo atributos) {
        setLocation(300, 400);
        initComponents();
        MOSTRAR(atributos);
    }

    public void MOSTRAR(MpegInfo atributos) {
        txtTag.setText("Titulo = " + atributos.getTitulo() + "\n"
                + "Artista = " + atributos.getArtista() + "\n"
                + "Album = " + atributos.getAlbum() + "\n"
                + "Track = " + atributos.getTrack() + "\n"
                + "Año = " + atributos.getaño() + "\n"
                + "Genero = " + atributos.getGenero());
        txtField.setText("Peso : " + atributos.getTamaño() + " bytes" + "\n"
                + atributos.getVersion() + " " + atributos.getLayer() + "\n"
                + atributos.getBitRate() / 1000 + " kbps" + "\n"
                + atributos.getSamplingRate() + " Hz " + atributos.getChannelsMode() + "\n"
                + "VBR : " + atributos.getVBR() + "\n" + "CRCs : " + atributos.getCRC() + "\n"
                + "Corporativo : " + atributos.getCopyright() + "\n"
                + "Original : " + atributos.getOriginal() + "\n"
                + "Enfasis : " + atributos.getEmphasis());
        lblUrl.setText("Archivo /URL : " + atributos.getDireccion().trim());
    }

    private void MOSTRAR(OggVorbisInfo atributos) {
        txtTag.setText("Titulo = " + atributos.getTitulo() + "\n" + "Artista = " + atributos.getArtista() + "\n" + "Album = " + atributos.getAlbum() + "\n" + "Track = " + atributos.getTrack() + "\n" + "Año = " + atributos.getaño() + "\n" + "Genero = " + atributos.getGenero());
        txtField.setText("Peso : " + atributos.getSize() + " bytes" + "\n"
                + "Promedio de Calidad : " + atributos.getAverageBitrate() + "\n"
                + "Calidad Nominal : " + atributos.getBitRate() / 1000 + " kbps" + "\n"
                + "Maxima Calidad : " + atributos.getMaxBitrate() + "\n"
                + "Minima Calidad : " + atributos.getMinBitrate() + "\n"
                + "Canales : " + atributos.getCanales() + "\n"
                + "Velocidad de muestreo : " + atributos.getSamplingRate() + "\n"
                + "Número de Serie : " + atributos.getSerial() + "\n"
                + "Version : " + atributos.getVersion() + "\n"
                + "Vendedor : " + atributos.getVendor());
        lblUrl.setText("Archivo /URL : " + atributos.getLocation().trim());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtTag = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblUrl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtField = new javax.swing.JTextArea();

        txtTag.setColumns(20);
        txtTag.setRows(5);
        jScrollPane1.setViewportView(txtTag);

        jLabel1.setText("Tag Estandar");

        jLabel2.setText("Informacion de Archivo");

        lblUrl.setText("Archivo /URL");

        txtField.setColumns(20);
        txtField.setEditable(false);
        txtField.setRows(5);
        jScrollPane2.setViewportView(txtField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(45, 45, 45))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblUrl))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUrl)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblUrl;
    public javax.swing.JTextArea txtField;
    public javax.swing.JTextArea txtTag;
    // End of variables declaration//GEN-END:variables

    private void MOSTRAR(WavInfo atributos) {
        txtTag.setText("Titulo = " + atributos.getTitulo() + "\n"
                + "Artista = " + atributos.getArtista() + "\n"
                + "Album = " + atributos.getAlbum() + "\n"
                + "Track = " + atributos.getTrack() + "\n"
                + "Año = " + atributos.getaño() + "\n"
                + "Genero = " + atributos.getGenero());
        txtField.setText("Peso : " + atributos.getTamaño() + " bytes" + "\n"
                + " " + atributos.getLayer() + "\n"
                + atributos.getBitRate() / 1000 + " kbps" + "\n"
                + atributos.getSamplingRate() + " Hz " + atributos.getChannelsMode() + "\n"
                + "VBR : " + atributos.getVBR() + "\n" + "CRCs : " + atributos.getCRC() + "\n"
                + "Corporativo : " + atributos.getCopyright() + "\n"
                + "Original : " + atributos.getOriginal() + "\n"
                + "Enfasis : " + atributos.getEmphasis());
        lblUrl.setText("Archivo /URL : " + atributos.getDireccion().trim());
    }

    private void MOSTRAR(FlacInfo atributos) {
        
    }

    private void MOSTRAR(APEInfo atributos) {

    }
}
