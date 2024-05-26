import java.util.Scanner;

/**
 * Clase Personaje: Permite la creación del personaje que usará el usuario dentro del juego, determinando sus
 * atributos, así como la gestión de inventario y salud
 */
public class Personaje {
    /**
     * Nombre del personaje
     */
    private final String nombre;
    /**
     * Cantidad de puntos de salud del personaje
     */
    private int vida;
    /**
     * ataque: Cantidad de puntos de ataque del personaje
     * defensa: Cantidad de puntos de defensa del personaje
     * desterza: Cantidad de puntos de destreza del personaje
     */
    private final int ataque, defensa, destreza;
    /**
     * Vector de ítems que almacenará el personaje a lo largo del juego
     */
    private final Item[] items;

    /**
     * Capacidad máxima del personaje, atendiendo a sus atributos
     */
    private final double maxPesoPorPersonaje;

    /**
     * @param nombre Nombre del personaje
     * @param vida Puntos de vida del personaje
     * @param ataque Puntos de ataque del personaje
     * @param defensa Puntos de defensa del personaje
     * @param destreza Puntos de destreza del personaje
     * @param maxItemsPorPersonaje Cantidad máxima de almacenamiento de ítems del personaje
     * @param maxPesoPorPersonaje Valor del peso máximo que podrá soportar el personaje
     */
    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje, double maxPesoPorPersonaje) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.destreza = destreza;
        this.maxPesoPorPersonaje = maxPesoPorPersonaje;
        this.items = new Item[maxItemsPorPersonaje];
    }

    /**
     * Metodo crearPersonaje que administra toda la generación de personajes
     * El metodo recoge todas las características del personaje mediante preguntas y respuestas por pantalla
     * y se controla que los valores introducidos sean validos. Una vez conseguidos todos los datos del personaje
     * se genera un objeto con dichas características.
     *
     * @param teclado Objeto de la clase Scanner
     * @return Personaje
     */
    public static Personaje crearPersonaje(Scanner teclado) {
        Personaje result;
        String nombre = Utilidades.leerCadena(teclado, "¿Cómo te llamas?:");

        System.out.println("¡Hola " + nombre + "! Tienes 250 puntos para repartir entre vida, ataque, " +
                "defensa y destreza.");

        int vida = Utilidades.leerNumero(teclado, "¿Cuánta vida quieres tener? (50-247):", 50, 247);
        int ataque = Utilidades.leerNumero(teclado, "¿Cuánto ataque quieres tener? (1-148):", 1, 148);
        int defensa = Utilidades.leerNumero(teclado, "¿Cuánta defensa quieres tener? (1-49):", 1, 49);
        int destreza = Utilidades.leerNumero(teclado, "¿Cuánta destreza quieres tener? (1-25):", 1, 25);

        int maxItems = destreza / 4;
        int pesoMax = ataque / 2;

        result = new Personaje(nombre, vida, ataque, defensa, destreza, maxItems, pesoMax);
        return result;
    }

    /**
     * Método getNombre: Devuelve el nombre del personaje
     *
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getVida: Devuelve los puntos de vida del personaje
     *
     * @return int vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método getAtaque: devuelve los puntos de ataque del personaje
     *
     * @return int ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método getDefensa: Devuelve los puntos de defensa del personaje
     *
     * @return int defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método getDestreza: Devuelve los puntos de destreza del personaje
     *
     * @return int destreza
     */
    public int getDestreza() {
        return destreza;
    }

    /**
     * Método getItems: Devuelve el vector de ítems que almacena el personaje
     *
     * @return Item[] items
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Método getItem que devuelve un Item según un índice dado.
     * Devuelve null si el índice no es válido, y el ítem si el índice es correcto
     *
     * @param indice Posición donde se desea buscar en el vector de ítems
     * @return Item
     */
    public Item getItem(int indice) {
        Item result = null;
        if (indice >= 0 && indice < items.length) {
            result = items[indice];
        }
        return result;
    }

    /**
     * Método recibirDanyo para actualizar la vida de un personaje.
     * Si el daño no es positivo, no hace nada. En caso contrario reduce la vida según el daño pasado
     *
     * @param danyo Cantidad de daño que se aplicará al personaje
     */
    public void recibirDanyo(int danyo) {
        if (danyo > 0) {
            if (danyo > vida) {
                this.vida = 0;
            } else this.vida -= danyo;
        }
    }

    /**
     * Método anyadirItem para incluir un item en la mochila del personaje.
     * Comprueba si el item es valido y si el peso max del personaje no se supera para poder incluir el item.
     * En caso de que no se cumpla devuelve false. En caso de que se pueda incluir, añade el ítem a la lista de
     * ítems del personaje y devuelve true
     *
     * @param item Ítem que se tratará de insertar en el vector de ítems del personaje
     * @return true si lo consigue insertar, false en caso contrario
     */
    public boolean anyadirItem(Item item) {
        boolean result = false;
        int pos = 0;
        if (item.getPeso() + this.getPesoMochila() <= maxPesoPorPersonaje) {
            while (pos < items.length && !result) {
                if (items[pos] == null) {
                    items[pos] = item;
                    result = true;
                } else pos++;
            }
        }
        return result;
    }

    /**
     * Método sobreescrito para devolver la información de un personaje
     * Devuelve un String con la información del personaje en el formato
     * descrito en la memoria de la práctica P.e: "{ Edgar (V: 20, A: 5, D: 2, X: 5) }"
     *
     * @return String
     */
    @Override
    public String toString() {
        return "{ " + this.nombre + " (V: " + this.vida + ", A: " + this.ataque + ", D: " + this.defensa +
                ", X: " + this.destreza + ") }";
    }

    /**
     * Método getPesoMochila para obtener el peso total que carga en la mochila el personaje
     * Recorre la lista de ítems para obtener el peso total de todos y devolverlo
     *
     * @return double
     */
    public double getPesoMochila() {
        double result = 0.0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) result += items[i].getPeso();
        }
        return result;
    }

    /**
     * Método getValorMochila para obtener el valor total que lleva entre todos los items el personaje.
     * Recorre la lista de items para obtener el valor total de todos y devolverlo
     *
     * @return double
     */
    public double getValorMochila() {
        double result = 0.0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) result += items[i].getValor();
        }
        return result;
    }

    /**
     * Método infoMochila para obtener en formato String la información de la mochila.
     * Recorre toda la lista de items del personaje para ir añadiendo la información de los items según el
     * formato mostrado en la memoria. P.e. "Mochila de Edgar:
     * Espada Mágica Peso: 1.5, Valor: 100
     * Armadura de Gromril Peso: 4, Valor: 300
     * Peso total: 5.5 Kg
     * Tu mochila vale 400 monedas"
     *
     * @return String
     */
    public String infoMochila() {
        StringBuilder sb = null;
        String result = "";
        sb = new StringBuilder();
        sb.append("Mochila de ").append(this.nombre).append(":");
        sb.append("\n");

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) sb.append(items[i].toString()).append("\n");
        }
        result = sb.toString();
        return result;
    }
}
