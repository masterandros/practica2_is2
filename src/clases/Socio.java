package clases;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Clase que representa a Socios de la Asociación.
 * @author Andros Peñas Olmos.
 * @author Josep Barberá Muñoz.
 */
public class Socio {

private int IDsocio;
private String nombre;
private static int proximoCodigoSocio=1;
private ArrayList<Moto> motosActuales;

    /**
     * Constructor con argumentos
     * @param nombre
     */
    public Socio(String nombre){
        IDsocio = proximoCodigoSocio;
        proximoCodigoSocio++;
        this.nombre=nombre;
        motosActuales = new ArrayList<Moto>();
    }

    /**
     * Contructor vacío con ArrayList de Motos.
     */
    public Socio() {
        motosActuales = new ArrayList<Moto>();
    }
    
    /**
     * Devuelve el id de socio.
     * @return
     */
    public int getIDsocio() {
            return IDsocio;
    }

    /**
     * Asigna el id del socio.
     * @param IDsocio
     */
    public void setIDsocio(int IDsocio) {
            this.IDsocio = IDsocio;
    }

    /**
     * Devuelve el nombre del socio.
     * @return
     */
    public String getNombre() {
            return nombre;
    }
    
    /**
     * Añade una moto con su fecha de cesión.
     * @param moto
     * @param fechaInicio
     */
    public void anyadirMotoActual(Moto moto, GregorianCalendar fechaInicio){
        if(!motosActuales.contains(moto)){
            motosActuales.add(moto);
        }
    }
    
    /**
     * Realiza un listado de todas las motos actuales.
     */
    public void listadoMotosActuales(){
         for (Moto moto : motosActuales) {
            System.out.println("  " + moto.toString());
        }
    }
    
    /**
     * Remueve o elimina una Moto.
     * @param moto
     */
    public void quitarMotoActual(Moto moto){
        motosActuales.remove(moto);
    }
    
    /**
     * Recoge el número de motos actuales.
     * @return
     */
    public int getNumMotosActuales(){
        return motosActuales.size();
    }

    /**
     * Recogo el ArrayList de motos actuales.
     * @return
     */
    public ArrayList<Moto> getMotosActuales() {
        return motosActuales;
    }
    
    /**
     * Recoge el precio de las motos actuales.
     * @return
     */
    public int getPrecioMotosActuales(){
        int precio = 0;
        for (Moto moto : motosActuales) {
            precio += moto.getPrecio();
        }
        return precio;
    }
    
    /**
     * Función utilizada para que cuando se lleve a cabo una nueva cesión
     * se controle o supervise que el crédito del socio.
     * @param credito
     * @return
     */
    public boolean checkNuevaCesion(int credito){
        return (credito+getPrecioMotosActuales()<=ListadoSocios.precioMaximoMoto);
    }

    /**
     * Sobreescritura del metodo toString para mostrar El ID del socio, el nombre y el dinero que le queda
     * además de la cesión
     * @return 
     */
    @Override
    public String toString() {
        String texto = "ID: " + IDsocio + " " + nombre + "\n";
        texto += "Le quedan " + (ListadoSocios.precioMaximoMoto-getPrecioMotosActuales()) + "€\n";
        int credito = ListadoSocios.precioMaximoMoto;
        for (Moto motoActual : motosActuales) {
            texto += "  " + motoActual + "\n";
            credito -= motoActual.getPrecio();
        }
        return texto;
    }
    
    
}