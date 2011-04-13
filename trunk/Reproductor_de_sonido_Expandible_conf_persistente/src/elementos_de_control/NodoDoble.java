package elementos_de_control;

/**
 *
 * @author JONATHAN1
 */
//Clase para una lista doble :
public class NodoDoble {

    public Direcciones Direccion;// informacion
    public NodoDoble ApuntAnt;	// enlace con el anterior
    public NodoDoble ApuntSgte;// enlace con el siguiente

    // constructor
    public NodoDoble(Direcciones Direccion) {
        this.Direccion = Direccion;
    }

    // get/set
    public void setNodo(Direcciones Direccion) {
        this.Direccion = Direccion;
    }

    public void setApuntSgte(NodoDoble ApuntSgte) {
        this.ApuntSgte = ApuntSgte;
    }

    public void setApuntAnt(NodoDoble ApuntAnt) {
        this.ApuntAnt = ApuntAnt;
    }

    public Direcciones getNodo() {
        return Direccion;
    }

    public NodoDoble getApuntSgte() {
        return ApuntSgte;
    }

    public NodoDoble getApuntAnt() {
        return ApuntAnt;
    }
}// fin de la clase de nodo doble

