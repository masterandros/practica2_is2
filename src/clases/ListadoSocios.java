package clases;
import java.util.ArrayList;

/**
 *
 * Clase que representa a Listado Socios de la asociación.
 * @author Andros Peñas Olmos.
 * @author Josep Barberá Muñoz.
 */
public class ListadoSocios {

    private ArrayList<Socio> socios = null;
    //public static int cuota = 60;   //Cuota del usuario
    public static int precioMaximoMotos = 6000; //Cuota del usuario * 100 = 6000€ siendo el máximo que puede gastar.

    /**
     * Listadomotos recoge un ArrayList de motos con un conjunto de ellas, predefinidas
     * como ejemplo que recoge la asociación Acama.
     */
    public ListadoSocios() {
        socios = new ArrayList<Socio>();
        Socio sc1 = new Socio("Luís");
        Socio sc2 = new Socio("Andros");
        Socio sc3 = new Socio("Josep");
        Socio sc4 = new Socio("Adrián");
        socios.add(sc1);
        socios.add(sc2);
        socios.add(sc3);
        socios.add(sc4);
    }

    /*public static int getCuota() {
        return cuota;
    }

    public static void setCuota(int cuota) {
        ListadoSocios.cuota = cuota;
    }*/

    public static int getPrecioMaximoMotos() {
        return precioMaximoMotos;
    }

    public static void setPrecioMaximoMotos(int precioMaximoMotos) {
        ListadoSocios.precioMaximoMotos = precioMaximoMotos;
    }

    
    
    //Buscar Socio por ID.
    public Socio buscarSocioId(int IDsocio) {
        for (Socio socio : socios) {
            if (socio.getIDsocio() == IDsocio) {
                return socio;
            }
        }
        return null;
    }

    //Buscar Socio por Nombre.
    public Socio buscarSocioNombre(String nombre) {
        for (Socio socio : socios) {
            if (socio.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                return socio;
            }
        }
        return null;
    }

    //Añadir socio.
    public void anyadirSocio(Socio socio) {
        socios.add(socio);
    }

    //Eliminar socio.
    public void eliminarSocio(Socio socio) {
        socios.remove(socio);
    }

    //GetSocios del ArrayList Socio.
    public ArrayList<Socio> getSocios() {
        return socios;
    }

    //Listamos los socios junto a su: ID, Nombre, MotosActuales, Crédito en €
    public void listarSocios() {
        for (Socio socio : socios) {
            System.out.println(socio.getIDsocio() + "-" + socio.getNombre() + " - " + socio.getNumMotosActuales() + " motos" + " - " + (precioMaximoMotos - socio.getPrecioMotosActuales()) + "€");
        }
    }

    //Listamos las motos cedidas, contemplamos el caso de que no hayan socios que tengan motos cedidas.
    public void listarSociosMotosCedidas() {
        boolean haberlosHaylos = false;
        for (Socio socio : socios) {
            if (socio.getMotosActuales().size() > 0) {
                System.out.println(socio.getIDsocio() + "-" + socio.getNombre() + " - Crédito: " + (ListadoSocios.precioMaximoMotos-socio.getPrecioMotosActuales()));
                socio.listadoMotosActuales();
                haberlosHaylos = true;
            }
        }
        if (!haberlosHaylos) {
            System.out.println("No hay socios con motos cedidas");
        }
    }

    //Busquéda de aquellas motos que han sido cesionadas a un socio.
    public Socio buscarCesionarioMoto(Moto motoCedida) {
        for (Socio socio : socios) {
            for (Moto moto : socio.getMotosActuales()) {
                if (moto == motoCedida) {
                    return socio;
                }
            }
        }
        return null;
    }
    
    //Función que Lista o muestra el crédito que le queda a un socio.
    public void listarSociosCredito(int credito) {
        for (Socio socio : socios) {
            if(socio.checkNuevaCesion(credito)){
                System.out.println(socio.getIDsocio() + "-" + socio.getNombre() + " - " + socio.getNumMotosActuales() + " motos" + " - " + (precioMaximoMotos - socio.getPrecioMotosActuales()) + "€");
            }
        }
    }
}
