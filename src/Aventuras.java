import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Aventuras desde donde lanzar la ejecución de la práctica
 */
public class Aventuras {

    /**
     * Main desde donde ejecutar el programa
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int filas = Integer.parseInt(args[0]);
        int columnas = Integer.parseInt(args[1]);
        int maxItems = Integer.parseInt(args[2]);
        int maxMonstruos = Integer.parseInt(args[3]);
        int maxTrampas = Integer.parseInt(args[4]);
        String ficheroSalas = args[5];
        String ficheroItems = args[6];
        String ficheroMonstruos = args[7];
        String ficheroTrampas = args[8];
        String ficheroPuntuaciones = args[9];
        Scanner teclado = new Scanner(System.in);
        Personaje jugador = Personaje.crearPersonaje(teclado);
        Motor engine = new Motor(filas, columnas, maxItems, maxMonstruos, maxTrampas);
        engine.iniciar(ficheroSalas, ficheroItems, ficheroMonstruos, ficheroTrampas);
        engine.jugar(teclado, jugador, new Random());
        guardarPuntuacion(ficheroPuntuaciones, jugador);
        mostrarPuntuaciones(ficheroPuntuaciones);
    }

    /**
     * Metodo guardarPuntuación en fichero
     * abrir y guardar en el fichero pasado como parametro el personaje
     * siguiendo el formato descrito en la memoria de la práctica
     *
     * @param ficheroPuntuaciones
     * @param jugador
     */
    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(ficheroPuntuaciones, true));
            printWriter.println(LocalDate.now() + "\t" + jugador.toString() + ", " +
                    jugador.getValorMochila() + " monedas");
        } catch (FileNotFoundException e) {
            System.err.println("Fichero de puntuacion no encontrado.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                printWriter.close();
            } catch (NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Metodo mostrarPuntuaciones del fichero puntuaciones
     * Mostrar por pantalla todas las puntuaciones almacenadas en el fichero
     * pasado como parámetro. P.e:
     * "Puntuaciones:
     * 2024-04-04	{ Raul (V: -4, A: 50, D: 40, X: 20) }, 420.0 monedas"
     *
     * @param ficheroPuntuaciones
     */
    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {
        System.out.println("Puntuaciones:");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(ficheroPuntuaciones));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichero de puntuaciones no encontrado.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
