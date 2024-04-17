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
            result = teclado.nextInt();
        } while (result < minimo || result > maximo);
        return result;
    }

    private static boolean esAlfabetico(String s) {
        String valido = "qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNMáéíóúÁÉÍÓÚ";
        boolean alfabetico = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (valido.indexOf(c) == -1) alfabetico = false;
        }
        return alfabetico;
    }
}
