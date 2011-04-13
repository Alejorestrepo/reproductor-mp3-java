/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos_de_control;

import java.io.File;

/**
 *
 * @author JONATHAN1
 */
public class Direcciones {

    private String Nombre;
    private File Direccion;

    // constructor vacio
    public Direcciones() {
    }

    // constructor que recibe un objeto con datos
    public Direcciones(Direcciones obj) {
        Nombre = obj.getNombre();
        Direccion = obj.getDireccion();
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public File getDireccion() {
        return Direccion;
    }

    public void setDireccion(File Direccion) {
        this.Direccion = Direccion;
    }
}
