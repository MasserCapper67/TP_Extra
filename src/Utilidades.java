import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase Utilidades
 */
public class Utilidades {

    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla
     * leer por pantalla y comprobar que es una cadena de caracteres válida.
     *
     * @param teclado
     * @param s
     * @return
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
     * leer por pantalla y comprobar que es un número valido. Solicita un número repetidamente hasta que se
     * introduzca uno correcto (dentro de los límites)
     *
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return
     */
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        teclado = new Scanner(System.in);
        int result = 0;
        do {
            System.out.println(mensaje);
            try {
                result = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor numérico.");
                leerNumero(teclado, mensaje, minimo, maximo);
            }
        } while (result < minimo || result > maximo);
        return result;
    }

    private static boolean esAlfabetico(String s) {
        String valido = " qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNMáéíóúÁÉÍÓÚ";
        boolean alfabetico = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (valido.indexOf(c) == -1) alfabetico = false;
        }
        return alfabetico;
    }

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
