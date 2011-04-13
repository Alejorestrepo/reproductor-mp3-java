
package reproductor;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Esta clase se implementa para manejar las excepciones del reproductor
 */
public class ReproductorExcepcion extends Exception
{
    public static final String GAINCONTROLNOTSUPPORTED = "Control de avance no soportado";
    public static final String PANCONTROLNOTSUPPORTED = "Pan control no soportado";
    public static final String WAITERROR = "Error a la espera";
    public static final String CANNOTINITLINE = "No puede la línea init";
    public static final String SKIPNOTSUPPORTED = "Salto no soportado";
    private Throwable cause = null;

    public ReproductorExcepcion()
    {
        super();
    }

    public ReproductorExcepcion(String msg)
    {
        super(msg);
    }

    public ReproductorExcepcion(Throwable cause)
    {
        super();
        this.cause = cause;
    }

    public ReproductorExcepcion(String msg, Throwable cause)
    {
        super(msg);
        this.cause = cause;
    }

    public Throwable getCause()
    {
        return cause;
    }

    /**
     * Returns the detail message string of this throwable. If it was
     * created with a null message, returns the following:
     * (cause==null ? null : cause.toString()).
     */

    public String getMessage()
    {
        if (super.getMessage() != null)
        {
            return super.getMessage();
        }
        else if (cause != null)
        {
            return cause.toString();
        }
        else
        {
            return null;
        }
    }

    public void printStackTrace()
    {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream out)
    {
        synchronized (out)
        {
            PrintWriter pw = new PrintWriter(out, false);
            printStackTrace(pw);
            pw.flush();
        }
    }

    public void printStackTrace(PrintWriter out)
    {
        if (cause != null) cause.printStackTrace(out);
    }
}
