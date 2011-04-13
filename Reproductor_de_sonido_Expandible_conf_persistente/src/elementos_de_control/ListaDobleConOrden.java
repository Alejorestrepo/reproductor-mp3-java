package elementos_de_control;

import java.io.File;

/**
 *
 * @author JONATHAN1
 */
public class ListaDobleConOrden {

    private NodoDoble inicio;
    private NodoDoble fin;

    // constructor
    public ListaDobleConOrden() {
        inicio = null;
        fin = null;
    }

    // get/set
    public void setInicio(NodoDoble inicio) {
        this.inicio = inicio;
    }

    public void setFin(NodoDoble fin) {
        this.fin = fin;
    }

    public NodoDoble getInicio() {
        return inicio;
    }

    public NodoDoble getFin() {
        return fin;
    }

    // metodos de administracion
    public void agrega(Direcciones direccion) {
        NodoDoble nuevo, auxiliar;

        //...preparar nuevo nodo ...
        nuevo = new NodoDoble(direccion);
        nuevo.setApuntSgte(null);
        nuevo.setApuntAnt(null);

        //...adicionar nuevo nodo a la lista...
        if (inicio == null) {
            //primer nodo
            inicio = nuevo;
            fin = inicio;
        } else //Por codigo orden
        if (direccion.getDireccion().compareTo(
                inicio.getNodo().getDireccion()) < 0) {
            //antes del primer nodo
            nuevo.setApuntSgte(inicio);
            inicio.setApuntAnt(nuevo);
            inicio = nuevo;
        } else if (direccion.getDireccion().compareTo(
                fin.getNodo().getDireccion()) > 0
                || direccion.getDireccion().compareTo(
                fin.getNodo().getDireccion()) == 0) {
            //despues del ultimo nodo
            nuevo.setApuntAnt(fin);
            fin.setApuntSgte(nuevo);
            fin = nuevo;
        } else {
            //entre dos nodos ya existentes
            auxiliar = inicio;

            // ubica el anterior
            while (direccion.getDireccion().compareTo(
                    auxiliar.getNodo().getDireccion()) > 0
                    || direccion.getDireccion().compareTo(
                    auxiliar.getNodo().getDireccion()) == 0) {
                auxiliar = auxiliar.getApuntSgte();
            }

            // enlaza
            nuevo.setApuntSgte(auxiliar);
            nuevo.setApuntAnt(auxiliar.getApuntAnt());
            auxiliar.getApuntAnt().setApuntSgte(nuevo);
            auxiliar.setApuntAnt(nuevo);
        }
    }
    //.....busca un codigo en la lista

    public NodoDoble busca(File codigo) {
        // empieza por el primero de la lista
        NodoDoble Auxiliar = inicio;

        // mientras no sea nulo
        while (Auxiliar != null) {
            if (codigo.equals(Auxiliar.getNodo().getDireccion())) {
                return Auxiliar;// lo encontro
            } else {
                Auxiliar = Auxiliar.getApuntSgte(); // pasa al siguiente
            }
        }// fin del while
        // termina la lista y no lo encontro
        return null;
    }

    public void elimina(NodoDoble aux) {
        if (inicio == fin && aux == inicio)//Si es el uniko
        {
            setInicio(null);
        } else if (aux == inicio)//El primer nodo
        {
            inicio = aux.getApuntSgte();
            inicio.setApuntAnt(null);
        } else if (aux.getApuntAnt() != null && aux.getApuntSgte() != null)//Eliminar el nodo del medio
        {
            aux.getApuntAnt().setApuntSgte(aux.getApuntSgte());
            aux.getApuntSgte().setApuntAnt(aux.getApuntAnt());
        } else if (aux == fin)//Si es el ultimo
        {
            aux.getApuntAnt().setApuntSgte(null);
            setFin(aux.getApuntAnt());

        }

    }
}
//fin de la clase ListaDobleConOrden

