import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase Utilidades: Clase que contiene métodos estáticos relacionados con la lectura de caracteres por teclado o el
 * manejo de estos
 *
 * @author Álvaro Bardají Robles
 * @author Sergio Arias
 * @version 1.0
 */
public class Utilidades {

    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla
     *
     * @param teclado objeto de la clase Scanner
     * @param s mensaje que se imprimirá por terminal
     * @return String result
     */
    public static String leerCadena(Scanner teclado, String s) {
        teclado = new Scanner(System.in);
        String result = null;
        boolean valid = true;
        do {
            System.out.println(s);
            result = teclado.nextLine();
        } while (!esAlfabetico(result));
        return result;
    }

    /**
     * Método estático leerNumero para leer un numero pasado por pantalla
     * Se lee por pantalla y  se comprueba que es un número valido. Solicita un número repetidamente hasta que se
     * introduzca uno correcto (dentro de los límites)
     *
     * @param teclado objeto de la clase Scanner
     * @param mensaje mensaje que se imprimirá por terminal
     * @param minimo valor mínimo que podrá tomar el número introducido dentro de ciertos límites
     * @param maximo valor máximo que podrá tomar el número introducido dentro de ciertos límites
     * @return int result
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int result = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println(mensaje);
            try {
                result = teclado.nextInt();
                if (result >= minimo && result <= maximo) {
                    valid = true;
                } else System.out.println("Introduzca un valor dentro de los límites (" + minimo + "-" + maximo + ").");
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un valor numérico.");
                teclado.next();
            }
        }
        return result;
    }

    /**
     * Método que en base a un String ingresado por el usuario comprueba si únicamente contiene caracteres alfabéticos
     *
     * @param s String del que se realizará la comprobación
     * @return true si únicamente contiene caracteres alfabéticos / false en caso contrario
     */
    private static boolean esAlfabetico(String s) {
        String valido = " qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNMáéíóúÁÉÍÓÚ";
        boolean alfabetico = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (valido.indexOf(c) == -1) alfabetico = false;
        }
        return alfabetico;
    }

    /**
     * Método que permite al usuario interactuar con su posición actual en el mapa. Únicamente se podrá introducir
     * un carácter atendiendo a los 4 posibles movimientos: N --> arriba / norte; S --> abajo / sur;
     * O --> izquierda / oeste; E --> derecha / este.
     *
     * @param teclado objeto de la clase Scanner
     * @param mensaje mensaje que se imprimirá por terminal
     * @return String movimiento
     */
    public static String leerMovimiento(Scanner teclado, String mensaje) {
        String movimiento = null;
        boolean correcto = false;
        while (!correcto) {
            System.out.println(mensaje);
            movimiento = teclado.nextLine();
            if (movimiento.equals("N".toUpperCase()) || movimiento.equals("S".toUpperCase())
            || movimiento.equals("O".toUpperCase()) || movimiento.equals("E".toUpperCase())) {
                correcto = true;
            }
        }
        return movimiento;
    }
}
