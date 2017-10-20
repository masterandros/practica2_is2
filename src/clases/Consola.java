package clases;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * Clase Consola, representa un cojunto de funciones o utilidades, para después
 * llamarlas desde Main cada vez y reaprovecharlas.
 * 
 * @author Andros Peñas Olmos.
 * @author Josep Barberá Muñoz.
 */

public class Consola {
    
    /**
     * Función para introducirCadena.
     * @param mensaje
     * @return
     */
    public static String introducirCadena(String mensaje){
        String cad = "";
        while(cad.equals("")){
            System.out.print(mensaje + ": ");
            BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
            try {
                cad = c.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cad;
    }
    
   
    /**
     * Función para Introducir un número entero.
     * @param mensaje
     * @return
     */
        public static int introducirEntero(String mensaje){
        int numero = -1;
        System.out.print(mensaje + ": ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String cadena = br.readLine();
            while(cadena.equals("")){
                System.out.print(mensaje + ": ");
                cadena = br.readLine();
            }
            if(esEnteroParseable(cadena)){
                numero = Integer.parseInt(cadena);  
            }

        } catch (IOException ex) {
            Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero;
    }
    

    
    /**
     * Función con el manejo de fechas a partir del GregorianCalendar.
     * Usamos el SimpledateFormat para ajustarlo según Días/Mes/Año.
     * @param mensaje
     * @return
     */
        
    public static GregorianCalendar introducirFecha(String mensaje){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        GregorianCalendar fecha = new GregorianCalendar(1970, 1, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cadena;
        while(fecha.getTimeInMillis() == (new GregorianCalendar(1970, 1, 1)).getTimeInMillis() || fecha == null){
            System.out.print(mensaje + " (dd/MM/yyyy) (01/01/1970=Cancelar): ");
            try {
                cadena = br.readLine();
                if(esFechaParseable(sdf, cadena)){
                    fecha.setTime(sdf.parse(cadena));
                }
            } catch (IOException ex) {
                Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fecha;
    }
    
    /**
     * Parseo de una Fecha.
     * @param sdf
     * @param cadena
     * @return
     */
    public static boolean esFechaParseable(SimpleDateFormat sdf, String cadena){
         try {
             return (sdf.parse(cadena) != null);
         } catch (ParseException ex) {
             Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
    }
    
    /**
     * Parseo de un número Entero.
     * @param cadena
     * @return
     */
    public static boolean esEnteroParseable(String cadena){
         try {
             return (new Integer(Integer.parseInt(cadena))) instanceof Integer;
         } catch (NumberFormatException ex) {
             return false;
         }
    }
}
