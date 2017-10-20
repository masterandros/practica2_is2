package clases;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import sun.util.calendar.Gregorian;

/**
 * Clase que representa a Motos de la Asociación.
 * @author Andros Peñas Olmos.
 * @author Josep Barberá Muñoz.
 */
public class Moto {

    private int IDmoto;             //Id de la moto                                        
    private String modelo;          //Modelo de la moto
    private int cilindrada;         //Cilindrada de la moto expresada en CC
    private int precio;             //Precio de la moto en €
    private String matricula;       //Matrícula del vehículo
    private static int proximoIDMoto = 0;   //Varible Static para controlar ID de moto.
    private ArrayList<Cesion> cesiones;     //Arraylist de Cesiones.

    /**
     * Constructor con argumentos
     * @param modelo
     * @param cilindrada
     * @param precio
     * @param matricula
     */
    public Moto(String modelo, int cilindrada, int precio, String matricula) {
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.precio = precio;
        this.matricula = matricula;
        IDmoto = ++proximoIDMoto; //Incremento del IDmoto.
        cesiones = new ArrayList<Cesion>();
    }

    /**
     * Constructor con argumentos, ID de la moto y ArrayList de Cesiones.
     */
    public Moto() {
        IDmoto = ++proximoIDMoto;
        cesiones = new ArrayList<Cesion>();
    }

    /**
     * Devuelve el ID de la moto.
     * @return
     */
    public int getIDmoto() {
        return IDmoto;
    }

    /**
     * 
     * @param IDmoto
     */
    public void setIDmoto(int IDmoto) {
        this.IDmoto = IDmoto;
    }

    /**
     * Devuelve el modelo de la moto.
     * @return
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * 
     * @param modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Devuelve la matricula de la moto.
     * @return
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Asigna la matricula de la moto.
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Devuelve la cilindrada de la moto.
     * @return
     */
    public int getCilindrada() {
        return cilindrada;
    }

    /**
     * Asigna cilindrade de la moto.
     * @param cilindrada
     */
    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    /**
     * Recoge el próximo Código de la moto.
     * @return
     */
    public static int getProximoCodigoMoto() {
        return proximoIDMoto;
    }

    /**
     * Asigna el código o ID de la moto.
     * @param proximoCodigoMoto
     */
    public static void setProximoCodigoMoto(int proximoCodigoMoto) {
        Moto.proximoIDMoto = proximoCodigoMoto;
    }

    /**
     * Devuelve el precio de la moto.
     * @return
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ID: " + IDmoto + " - Modelo: " + modelo + " - Cilindrada: " + cilindrada + " - " + "Matrícula: " + matricula + " - " + precio + "€";
    }

    /**
     *
     * @param socio
     * @param fechaInicio
     */
    public void anyadirCesion(Socio socio, GregorianCalendar fechaInicio) {
        cesiones.add(new Cesion(this, socio, fechaInicio));
        socio.anyadirMotoActual(this, fechaInicio);
    }

    /**
     * Devuelve las cesiones del ArrayList.
     * @return
     */
    public ArrayList<Cesion> getCesiones() {
        return cesiones;
    }

    /**
     * Control de las cesiones.
     * @return
     */
    public Cesion getCesionActual() {
        if (cesiones.size() == 0) {
            return null;
        } else {
            return cesiones.get(cesiones.size() - 1);
        }
    }
}