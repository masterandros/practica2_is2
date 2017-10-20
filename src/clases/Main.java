package clases;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Clase Main, representa la clase principal de nuestro programa, con sus 
 * diferentes casos en un Switch, junto a las funciones necesarias que determinan
 * cada una de las opciones del menú.
 * 
 * @author Andros Peñas Olmos.
 * @author Josep Barberá Muñoz.
 */
public class Main {
    private int opcion;
    //String concantenado con todo el menú
    private String miMenu = "\n"
            + "1 - Registrar un nuevo miembro.\n"
            + "2 - Registrar una nueva motocicleta.\n"
            + "3 - Registrar una cesión.\n"
            + "4 - Listar en pantalla los miembros con motos en posesión.\n"
            + "5 - Listar todas las motos.\n"
            + "6 - Mostrar las cesiones realizadas.\n"
            + "7 - Salir del programa.\n\n";
    ListadoSocios ls;
    ListadoMotos lm;

    /**
     * Asignamos las motos prestablecidas con cada uno de sus Ids
     * a varios usuarios ya dados previamente de alta en la asociación.
     * 
     */
    public void menu() {
        ls = new ListadoSocios();
        lm = new ListadoMotos();
        for (Moto moto : lm.getMotos()) {
            boolean asignada = false;
            for(int i = 0 ; i < ls.getSocios().size() && !asignada ; i++){
                Socio socio = ls.getSocios().get(i);
                if(socio.checkNuevaCesion(moto.getPrecio())){
                    socio.anyadirMotoActual(moto, new GregorianCalendar());
                    moto.anyadirCesion(socio, new GregorianCalendar());
                    asignada = true;
                    i--;
                }
            }
        }
        //Título de la Asociación ACAMA
        System.out.print("Práctica 1 - Acama (Asociación de amigos de motos antiguas) \n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (opcion != 7) {
            System.out.print(miMenu);
            opcion = Consola.introducirEntero("Introduce tu opción");
            System.out.println();
            switch (opcion) {
                case 1: {
                    registrarSocio(); //Registrar un Socio en la asociación.              
                    break;
                }
                case 2: {
                    registrarMoto(null); //Registrar una Moto en la asociación.
                    break;
                }

                case 3: {
                    registrarCesion(); //Registrar una nueva cesión.
                    break;
                }

                case 4: {
                    listarSociosCesion(); //Listar los socios de una cesión.
                    break;
                }

                case 5: {
                    listarMotos(); //Listar todas las motos.
                    break;
                }

                case 6: {
                    listarCesiones(); //Listar todas las cesiones
                    break;
                }
                case 7: {
                    guardarFichero(); //Guardar archivo con nombre personalizado y volcar datos.
                    break;
                }
                default: {
                    break;
                }
            }
        }
        System.exit(0); //Salida
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Main principal = new Main();
        principal.menu();

    }

    /**
     * Función que registra un Socio y comprueba en caso de que exista
     * el socio, que no puede volver a darse de alta porque ya existe.
     */
    public void registrarSocio() {
        String nombre = Consola.introducirCadena("Introduce el nombre (espacio = Cancelar)");
        Socio socio1 = ls.buscarSocioNombre(nombre);

        if (!nombre.equals("")) {

            if (socio1 != null) {
                System.out.println("El socio " + socio1.getNombre() + " ya existe con ID: " + socio1.getIDsocio());
                nombre = Consola.introducirCadena("Introdu1ce el nombre (espacio = Cancelar)");
            } else {
                Socio socio = new Socio(nombre);
                ls.anyadirSocio(socio);
                System.out.println("El Socio ha sido añadido");
            }
        }
    }

    /**
     * Función que registra una Moto y comprueba adicionalmente el resto de crédito o
     * saldo restante en base a la cuota mensual máxima de 6000€ menos el coste de la moto.
     * 
     * Además también comprueba que si ningún socio tiene el suficiente crédito, no podrá
     * quedarse dicha moto.
     * 
     * Por otro lado, en el caso de que el socio cumpla las condiciones establecidas con el crédito, nos permitirá
     * añadir una moto y una vez añadida asignar dicha moto a un usuario.
     * @param propietario
     */
    public void registrarMoto(Socio propietario) {
        Moto m = new Moto(); //Constructor nuevo y vacio en moto
        m.setPrecio(Consola.introducirEntero("Introduce el precio (espacio = Cancelar)"));
        if (m.getPrecio() != -1){
            int creditoMaximo = 0;
            for (Socio socio : ls.getSocios()) {
                if (6000 - socio.getPrecioMotosActuales() >= creditoMaximo) {
                    creditoMaximo = 6000 - socio.getPrecioMotosActuales();
                }
            }
            if (creditoMaximo <= m.getPrecio()) {
                m = null;
                System.gc();
                System.out.println("Ningún socio puede quedarse la moto");
            } else {
                m.setModelo(Consola.introducirCadena("Introduce el modelo"));
                m.setCilindrada(Consola.introducirEntero("Introduce la cilindrada"));
                m.setMatricula(Consola.introducirCadena("Introduce la matrícula"));
                Socio cesionario = null;
                if (m.getCesionActual() != null) {
                    cesionario = m.getCesionActual().getCedido();
                }
                lm.añadirMoto(m);
                if (cesionario != null) {
                    cesionario.quitarMotoActual(m);
                }
                while (cesionario == null) {
                    ls.listarSociosCredito(m.getPrecio());
                    cesionario = ls.buscarSocioId(Consola.introducirEntero("Introduce el número del cesionario de la moto"));
                    if (cesionario.checkNuevaCesion(m.getPrecio())) {
                        m.anyadirCesion(cesionario, new GregorianCalendar());
                    }
                }
                ls.listarSocios();
                System.out.println("La moto se ha registrado");

            }    
        }
        
    }

    /**
     * 
     * Función que registra una cesion consulta los IDS de las motos disponibles, pregunta
     * a quién queremos cederle dicha moto y realiza las comprobaciones de:
     *  1. No podamos cedernos una moto a nosotros mismos, ya que somos los propietarios y lo que queremos hacer es cederla a otro.
     *  2. Que el Socio al que quedamos ceder la moto, no podamos cedersela porque haya superado el límite de los 6000€.
     */
    public void registrarCesion() {
        if(lm.listarMotos()){
            Moto moto = lm.buscarMotoID(Consola.introducirEntero("Introduce la ID de la moto"));
            ls.listarSociosCredito(moto.getPrecio());
            Socio cedido = ls.buscarSocioId(Consola.introducirEntero("Introduce el número de socio al que vas a ceder la moto"));
            Socio cesionario = ls.buscarCesionarioMoto(moto);
            if (cedido.checkNuevaCesion(moto.getPrecio()) && !cesionario.equals(cedido)) {
                if (cesionario != null) {
                    cesionario.quitarMotoActual(moto);
                }
                GregorianCalendar fechaInicio = Consola.introducirFecha("¿Desde cuando la quieres ceder?");
                cedido.anyadirMotoActual(moto, fechaInicio);
                moto.anyadirCesion(cedido, fechaInicio);
                System.out.println("Moto cedida");
            } else {
                if(cesionario.equals(cedido)){
                 System.out.println("No se puede ceder una moto a usted mismo");    
                }else
                System.out.println("No es posible ceder la moto, se ha superado el limite de 6000€");
            } 
        }
       
        
    }

    /**
     * Función que lista en pantalla los miembros con motos en posesión
     */
    public void listarSociosCesion() {
        ls.listarSociosMotosCedidas();
    }

    /**
     * Función que lista en pantalla todas las motos de las que dispone la asociación.
     */
    public void listarMotos() {
        lm.listarMotos();
    }

    /**
     * Función que muestra todas las cesiones realizadas entre los distinto socios de la asociación.
     */
    public void listarCesiones() {
        lm.listarHistorialCesiones();
    }
    
    
    /**
     * Función guardarFichero.
     * Lo primero que hacemos es en una variable nombre fuerzo a que se guarde en la ruta src\archivo donde archivo es nuestro 
     * paquete dentro del proyecto, pedimos un println con el nombre del fichero y a lo que añada el usuario lo concatenamos con .txt
     * 
     * Hacemos un println donde informamos que estamos guardando el fichero y guardamos:
     *  1. Los socios.
     *  2. Las motos.
     *  3. Las cesiones realizadas.
     * 
     *  Contemplamos el caso adicional de que no se pueda abrir el fichero.
     */

    private void guardarFichero() {
        String nombre = "src\\archivo\\" + Consola.introducirCadena("Introduce el nombre del fichero") + ".txt";
        try {
            System.out.print("Guardando fichero ");
            FileWriter fw = new FileWriter(nombre);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Socios: \n");
            for (Socio socio : ls.getSocios()) {
                pw.println(socio.toString());
            }
            System.out.print(".");
            pw.println("\nMotos:");
            for (Moto moto : lm.getMotos()) {
                pw.println(moto.toString());
            }
            System.out.print(".");
            pw.println("\nCesiones:");
            for (Moto moto : lm.getMotos()) {
                for (Cesion cesion : moto.getCesiones()) {
                    pw.println(cesion.toString());
                }
            }
            pw.close();
            System.out.print(".\n");
            System.out.println("Se ha guardado el fichero");
        } catch (IOException ex) {
            System.err.println("No se puede abrir el fichero \"" + nombre + "\"");
        }
    }
}
