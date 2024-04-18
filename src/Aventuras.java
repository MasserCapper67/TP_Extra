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
     * TODO instanciación e inicialización de objetos para la ejecución,
     *  ejecución del motor, muestra de puntuaciones y lectura de instrucciones
     *  por teclado para jugar. Finalmente guardar la puntuación
     *
     * @param args
     */
    public static void main(String[] args) {

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
            printWriter = new PrintWriter(new FileWriter(ficheroPuntuaciones));
            printWriter.print(LocalDate.now() + "\t" + jugador.toString() + ", " +
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
