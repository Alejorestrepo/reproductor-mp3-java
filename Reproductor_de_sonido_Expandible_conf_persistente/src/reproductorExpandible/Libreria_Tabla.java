/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductorExpandible;

import elementos_de_control.Direcciones;
import elementos_de_control.NodoDoble;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import reproductor.ReproductorExcepcion;

/**
 *
 * @author JONATHAN
 */
public class Libreria_Tabla{

    private static DefaultTableModel miModelo;
    private static javax.swing.JTable Tabla;
    static Direcciones Direccion;
    public static int eliminados = 0;
    GUIReproductor abc;
    Libreria_Tabla(JTable Tabla, DefaultTableModel miModelo, GUIReproductor abc) {
        Libreria_Tabla.Tabla = Tabla;
        Libreria_Tabla.miModelo = miModelo;
        this.abc = abc;
    }

    static JTable getMiTabla() {
        return Tabla;
    }

    public static DefaultTableModel getMiModelo() {
        return miModelo;
    }

    public File[] llamar(File Dir) {
        File[] lista_Archivos = Dir.listFiles();
        LlenarTabla(lista_Archivos);
        return lista_Archivos;
    }

    public static void ActualizaTabla() {
        inicializaTabla();
        NodoDoble auxiliar;
        auxiliar = GUIReproductor.ldco.getInicio();
        while (auxiliar != null) {
            Direcciones dir = auxiliar.getNodo();
            Object[] datos = {dir.getNombre(), dir.getDireccion()};
            getMiModelo().addRow(datos);
            //retrocede al nodo anterior
            auxiliar = auxiliar.getApuntSgte();
        }

    }

    public static void BuscarIrreproducibles() {
        for (int i = 0; i < getMiTabla().getRowCount(); ++i) {
            String cadarchivo = getMiTabla().getValueAt(i, 1).toString();
            try {
                Libreria.loadFile(cadarchivo);
            }
            catch (ReproductorExcepcion ex) {
                NodoDoble auxiliar = GUIReproductor.ldco.busca(new File(cadarchivo));
                if (auxiliar != null) {
                    GUIReproductor.ldco.elimina(auxiliar);
                    eliminados++;
                }
            }
        }
        ActualizaTabla();
    }

    public static void inicializaTabla() {
        // obtiene numero de filas de la tabla
        int filas = getMiTabla().getRowCount();
        // remueve todas las filas de la tabla
        for (int fila = 0; fila < filas; fila++) {
            getMiModelo().removeRow(0);
        }
    }

    public void LlenarTabla(File[] Elementos) {
        GUIReproductor.EliminarElegido.setEnabled(true);
        GUIReproductor.EliminarTodo.setEnabled(true);
        GUIReproductor.GuardarLista.setEnabled(true);
        int tamaño = Elementos.length;
        for (int t = 0; t < tamaño; t++) {
            if (Elementos[t].isFile())//Verificar que es un archivo y no una carpeta
            {
                if (Validaciones(Elementos[t], Libreria.extencion_archivo)) {
                    Enviar(Elementos[t].getName(), Elementos[t]);
                }
            }
            else {
                llamar(Elementos[t]);
                //System.out.println(Elementos[t].getName());
            }

        }
    }
    public static boolean Validaciones(File files, String[] extenciones) {
        boolean estado = false;
            for (int j = 0; j < extenciones.length; j++)//Comparador interno falto implementar
            {
                //corrige el bug que evitava agregar otro archivo q no sea mp3
                estado = estado || files.getName().contains(extenciones[j]);
            }
         return estado;
    }
    public static boolean Validaciones(String files, String[] extenciones) {
        boolean estado = false;
            for (int j = 0; j < extenciones.length; j++)//Comparador interno falto implementar
            {
                //corrige el bug que evitava agregar otro archivo q no sea mp3
                estado = estado || files.contains(extenciones[j]);
            }
        return estado;
    }
    public static void IngresaDatos(String nombre, File file) {
        Direccion = new Direcciones();
        Direccion.setDireccion(file);
        Direccion.setNombre(nombre);
    }

    public static void Traer_Lista(File file) {
        FileReader LeerArchivo = null;
        BufferedReader Temporal_memoria = null;
        try {
            LeerArchivo = new FileReader(file);
            Temporal_memoria = new BufferedReader(LeerArchivo);
            // Lectura del fichero
            String linea;
            while ((linea = Temporal_memoria.readLine()) != null) {
                if (linea.contains("#EXT") == false && Validaciones(linea,Libreria.extencion_archivo))//Evita leer metadata de winamp
                {
                    Enviar(new File(linea).getName(), new File(linea));
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Cargando Archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void Enviar(String nombre, File archivo) {
        IngresaDatos(nombre, archivo);
        if (GUIReproductor.noreproducible) {
            try {
                Libreria.loadFile(archivo.toString());
            }
            catch (ReproductorExcepcion ex) {
                BuscarIrreproducibles();
            }
        }
        if (GUIReproductor.duplicado) {
            NodoDoble aux = GUIReproductor.ldco.busca(archivo);
            if (aux == null) {
                GUIReproductor.ldco.agrega(Direccion);
                Object[] datos = {Direccion.getNombre(), Direccion.getDireccion()};
                getMiModelo().addRow(datos);
                GUIReproductor.Habilitar(true);
            }
        }
        else {
            GUIReproductor.ldco.agrega(Direccion);
            Object[] datos = {Direccion.getNombre(), Direccion.getDireccion()};
            getMiModelo().addRow(datos);
            GUIReproductor.Habilitar(true);
        }
    }

    public void Reproduce(Object rutaTabla) {
        int filas = getMiTabla().getRowCount();
        for (int h = 0; h < filas; h++) {
            if (rutaTabla.toString().equals(getMiTabla().getValueAt(h, 1).toString())) {
                abc.metodos_internos.Reproducir(h);
                getMiTabla().changeSelection(h, 1, false, false);
            }
        }
    }
}
