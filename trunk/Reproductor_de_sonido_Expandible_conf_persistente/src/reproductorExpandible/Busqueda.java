package reproductorExpandible;

import elementos_de_control.ListaDobleConOrden;
import elementos_de_control.Direcciones;
import elementos_de_control.NodoDoble;
import java.io.File;
import javax.swing.DefaultListModel;

public class Busqueda extends javax.swing.JFrame {

    static ListaDobleConOrden ldco;
    Direcciones Direccion;
    static Tabla tabla;
    DefaultListModel Elementos;
    Object Nombre_item;
    File Direccion_Archivo;
    GUIReproductor abc;

    public Busqueda(ListaDobleConOrden ldco1, GUIReproductor un, Tabla tabla) {
        Busqueda.ldco = ldco1;
        this.abc = un;
        initComponents();
        setLocation(400, 400);
        setSize(445, 400);
        txtTexto.setText("");
        setLocation(288, 219);
        Elementos = new DefaultListModel();
        Lista.setModel(Elementos);
        this.tabla = tabla;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel1 = new javax.swing.JPanel();
        txtTexto = new javax.swing.JTextField();
        Etiqueta = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnReproducir = new javax.swing.JButton();
        PanelDeslizable = new javax.swing.JScrollPane();
        Lista = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ir a archivo");

        txtTexto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTextoKeyTyped(evt);
            }
        });

        Etiqueta.setText("  Buscar texto");

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addComponent(Etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
            .addComponent(txtTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Etiqueta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnReproducir.setText("Reproducir");
        btnReproducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReproducirActionPerformed(evt);
            }
        });

        Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaMousePressed(evt);
            }
        });
        PanelDeslizable.setViewportView(Lista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReproducir, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(PanelDeslizable, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelDeslizable, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnReproducir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtTextoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextoKeyTyped
        BusquedaAproximada();
    }//GEN-LAST:event_txtTextoKeyTyped

    private void ListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaMousePressed
        Nombre_item = Lista.getSelectedValue();
        //System.out.println(Nombre_item);
        Direccion_Archivo = Buscar_Direccion(Nombre_item);
        //System.out.println(direcc);
    }//GEN-LAST:event_ListaMousePressed

    private void btnReproducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReproducirActionPerformed
        Reproduce(Direccion_Archivo);
    }//GEN-LAST:event_btnReproducirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Etiqueta;
    private javax.swing.JList Lista;
    private javax.swing.JPanel Panel1;
    private javax.swing.JScrollPane PanelDeslizable;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnReproducir;
    private javax.swing.JTextField txtTexto;
    // End of variables declaration//GEN-END:variables

    /*
     * Realiza la busqueda por cadenas de archivo
     */
    public void BusquedaAproximada() {
        
        String texto = txtTexto.getText();
        if (texto.compareTo("") == 0) {
            Elementos.clear();
            NodoDoble auxiliar = ldco.getInicio();
            while (auxiliar != null) {
                Direccion = auxiliar.getNodo();
                Elementos.addElement(Direccion.getNombre());
                //retrocede al nodo anterior
                auxiliar = auxiliar.getApuntSgte();
            }
        } else {
            Elementos.clear();
            NodoDoble auxiliar1 = ldco.getInicio();
            while (auxiliar1 != null) {
                Direccion = auxiliar1.getNodo();
                if (Direccion.getNombre().indexOf(texto) != -1) {
                    Elementos.addElement(Direccion.getNombre());
                }
                //retrocede al nodo anterior
                auxiliar1 = auxiliar1.getApuntSgte();
            }
        }
    }

    public File Buscar_Direccion(Object Nombre_item) {
        File archivo = null;
        String texto = String.valueOf(Nombre_item);
        NodoDoble auxiliar1 = ldco.getInicio();
        while (auxiliar1 != null) {
            Direccion = auxiliar1.getNodo();
            if (Direccion.getNombre().indexOf(texto) != -1) {
                archivo = Direccion.getDireccion();
            }
            //retrocede al nodo anterior
            auxiliar1 = auxiliar1.getApuntSgte();
        }
        return archivo;
    }

    public void Reproduce(File Direccion_Archivo) {
        String ruta = Direccion_Archivo.toString();
        //System.out.println(Direccion_Archivo);
        int filas = tabla.getMiTabla().getRowCount();
        for (int h = 0; h < filas; h++) {
            if (ruta == tabla.getMiTabla().getValueAt(h, 1).toString()) {
                abc.Reproducir(h);
                tabla.getMiTabla().changeSelection(h, 1, false, false);
            }
        }
    }
}
