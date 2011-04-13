
package reproductor;

import java.util.Collection;
import java.util.Iterator;

/**
 * This class implements a threaded events launcher.
 */
public class ReproductorLanzarEvento extends Thread
{
    private int code = -1;
    private int position = -1;
    private double value = 0.0;
    private Object description = null;
    private Collection listeners = null;
    private Object source = null;

    /**
     * Contructor.
     * @param code
     * @param position
     * @param value
     * @param description
     * @param listeners
     * @param source
     */
    public ReproductorLanzarEvento(int code, int position, double value, Object description, Collection listeners, Object source)
    {
        super();
        this.code = code;
        this.position = position;
        this.value = value;
        this.description = description;
        this.listeners = listeners;
        this.source = source;
    }

    public void run()
    {
        if (listeners != null)
        {
            Iterator it = listeners.iterator();
            while (it.hasNext())
            {
                ReproductorLanzador bpl = (ReproductorLanzador) it.next();
                ReproductorEvento event = new ReproductorEvento(source, code, position, value, description);
                bpl.estadoActualizado(event);
            }
        }
    }
}
