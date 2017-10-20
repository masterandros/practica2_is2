package clases;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * Clase cesión, representa las cesiones que hay con su constructor y la fecha 
 * de la misma.
 * 
 * @author Andros Peñas Olmos.
 * @author Josep Barberá Muñoz.
 */
public class Cesion {
    private GregorianCalendar fecha; //Lo utilizamos para la fecha de cesiones.
    private Socio cedido;
    private Moto moto;
    
    /**
     * Clase con argumentos
     * @param moto Moto.
     * @param cedido Nombre del socio cedido.
     * @param fecha Fecha de cesión
     */
    public Cesion(Moto moto, Socio cedido, GregorianCalendar fecha) {
        this.moto = moto;
        this.cedido=cedido;
        this.fecha=fecha;
    }

    /**
     * Get de Fecha 
     * @return
     */
    public GregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Get de Cedido
     * @return
     */
    public Socio getCedido() {
        return cedido;
    }

    /**
     * Sobreescritura del metodo toString para mostrar El día, mes y año de la cesión
     * además de la cesión
     * @return 
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMM YYYY");
        return "En " + sdf.format(fecha.getTime()) + ", " + cedido.getNombre() + " cedió la moto " + moto;
    }
}
