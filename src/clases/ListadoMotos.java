package clases;
import java.util.ArrayList;
import java.util.Comparator;
 
/**
 *
 * Clase que representa a Listado Motos de la Asociación.
 * @author Andros Peñas Olmos.
 * @author Josep Barberá Muñoz.
 */
public class ListadoMotos {
    private ArrayList<Moto> motos = null;

    /**
     * Listadomotos recoge un ArrayList de motos con un conjunto de ellas, predefinidas
     * como ejemplo que recoge la asociación Acama.
     */
    public ListadoMotos() {
        motos = new ArrayList<Moto>();
        motos.add(new Moto("Vespa Primavera",125,2500,"1234BCD"));
        motos.add(new Moto("Vespa Primavera",125,2500,"2345CDF"));
        motos.add(new Moto("Motobenae Poney AG2",70,2300,"3456DFG"));
        motos.add(new Moto("Bultaco",200,3800,"3456FGH"));
        motos.add(new Moto("Guzzi Cardelino 73",75,1200,"4567GHJ"));
        motos.add(new Moto("Duccati mini",49,4000,"5678HJK"));
    }
    
    /**
     * Añade una moto.
     * @param moto
     */
    public void añadirMoto(Moto moto){
        motos.add(moto);
    }
    
    /**
     * Remueve una moto.
     * @param moto
     * @return
     */
    public boolean quitarMoto(Moto moto){
        return motos.remove(moto);
    }
    
    public String toString(){
        String texto = "";
        for (Moto moto : motos) {
            texto += moto.toString();
        }
        return texto;
    }
    
    /**
     * Función que lista el historial de las cesiones realizadas.
     */
    public void listarHistorialCesiones(){
        ArrayList<Cesion> cesiones = new ArrayList<Cesion>();
        for (Moto moto : motos){
            cesiones.addAll(moto.getCesiones());
        }
        cesiones.sort(new Comparator<Cesion>() {

            @Override
            public int compare(Cesion cesion1, Cesion cesion2) {
                return cesion1.getFecha().compareTo(cesion2.getFecha());
            }
        });
        for (Cesion cesion : cesiones) {
            System.out.println(cesion.toString());
        }
    }
    
    /**
     * Función que busca una moto según su ID.
     * @param IDmoto
     * @return
     */
    public Moto buscarMotoID(int IDmoto){
        for (Moto moto : motos) {
            if(moto.getIDmoto()== IDmoto){
                return moto;
            }
        }
        return null;
    }

    /**
     * Función que Lista las motos disponibles, en caso de que no existan
     * no hay motos.
     * @return
     */
    public boolean listarMotos(){
        boolean haberlosHaylos = false;
        for (Moto moto : motos) {
            System.out.println(moto.toString());
            haberlosHaylos = true;
        }
        if (!haberlosHaylos) {
            System.out.println("No hay motos");
        }
        return haberlosHaylos;
    }

    /**
     * GetMotos del ArrayList moto.
     * @return
     */
    public ArrayList<Moto> getMotos() {
        return motos;
    }  
}
