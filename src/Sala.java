import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase Sala: Contiene la información y permite el manejo de las distintas entidades que contiene una
 * sala / habitación dentro del mapa. Una sala puede estar constituida tanto por monstruos, trampas u objetos
 */
public class Sala {
    /**
     * Parámetro que contiene la descripción de la sala
     */
    private final String descripcion;
    /**
     * Parámetro que contiene el vector de ítems de la sala
     */
    private final Item[] items;
    /**
     * Parámetro que contiene el vector de monstruos de la sala
     */
    private final Monstruo[] monstruos;
    /**
     * Parámetro que contiene el vector de trampas de la sala
     */
    private final Trampa[] trampas;

    /**
     * Parámetro que indica la fila de la sala dentro del mapa
     */
    private final int fila;
    /**
     * Parámetro que indica la columna de la sala dentro del mapa
     */
    private final int columna;

    /**
     * Constructor de clase para inicializar los atributos de clase
     *
     * @param descripcion       descripción de la sala
     * @param max_items         número máximo de items que puede contener la sala
     * @param max_monstruos     número máximo de monstruos que puede contener la sala
     * @param maxTrampasPorSala número máximo de trampas que puede contener la sala
     * @param fila              fila de la sala dentro del mapa
     * @param columna           columna de la sala dentro del mapa
     */
    public Sala(String descripcion, int max_items, int max_monstruos, int maxTrampasPorSala, int fila, int columna) {
        this.descripcion = descripcion;
        this.items = new Item[max_items];
        this.monstruos = new Monstruo[max_monstruos];
        this.trampas = new Trampa[maxTrampasPorSala];
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Método agregarItem para incluir items en la sala.
     * Compueba si existe el objeto en la sala o si la lista de items no está ya llena
     *
     * @param item objeto que se tratará de introducir al vector de ítems de la sala
     * @return true si consigue agregar el ítem / false en caso contrario
     */
    public boolean agregarItem(Item item) {
        boolean agregado = false;
        boolean repetido = false;
        int posVacia = -1;

        for (int i = 0; i < this.items.length; i++) {
            if (items[i] != null && items[i].equals(item)) repetido = true;
            if (items[i] == null && posVacia == -1) posVacia = i;
        }
        if (!repetido && posVacia != -1) {
            items[posVacia] = item;
            agregado = true;
        }
        return agregado;
    }

    /**
     * Método agregarMonstruo para incluir un monstruo en la sala.
     * Comprueba si existe el monstruo en la sala o si la lista de monstruos no está ya llena
     *
     * @param monstruo objeto monstruo que se tartará de introducir en el vector de monstruos de la sala
     * @return true si consigue agregar el monstruo / false en caso contrario
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        boolean agregado = false;
        boolean repetido = false;
        int posVacia = -1;

        for (int i = 0; i < this.monstruos.length; i++) {
            if (monstruos[i] != null && monstruos[i].equals(monstruo)) repetido = true;
            if (monstruos[i] == null && posVacia == -1) posVacia = i;
        }
        if (!repetido && posVacia != -1) {
            monstruos[posVacia] = monstruo;
            agregado = true;
        }
        return agregado;
    }

    /**
     * Método agregarTrampa para incluir una trampa en la sala.
     * Comprueba si existe la trampa en la sala o si la lista de trampas no está ya llena
     *
     * @param trampa objeto trampa que se tratará de insertar en el vector de trampas de la sala
     * @return true si consigue agregar la trampa / false en caso contrario
     */
    public boolean agregarTrampa(Trampa trampa) {
        boolean agregado = false;
        boolean repetido = false;
        int posVacia = -1;

        for (int i = 0; i < this.trampas.length; i++) {
            if (trampas[i] != null && trampas[i].equals(trampa)) repetido = true;
            if (trampas[i] == null && posVacia == -1) posVacia = i;
        }
        if (!repetido && posVacia != -1) {
            trampas[posVacia] = trampa;
            agregado = true;
        }
        return agregado;
    }

    /**
     * Método getDescripcion: Devuelve la descripción de la sala
     *
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método hayMonstruos para comprobar si hay algún monstruo en la sala
     *
     * @return true si hay al menos un monstruo en la sala / false si no hay ninguno
     */
    public boolean hayMonstruos() {
        boolean result = false;
        int pos = 0;
        while (pos < monstruos.length && !result) {
            if (monstruos[pos] == null) pos++;
            else result = true;
        }
        return result;
    }

    /**
     * Método seleccionarMonstruo para introducir desde pantalla el nombre de un monstruo.
     * Muestra por pantalla todos los monstruos y luego solicita que se introduzca el nombre del monstruo que se
     * quiere seleccionar
     *
     * @param teclado objeto de la clase Scanner
     * @return Monstruo atacado
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) {
        Monstruo atacado = null;
        String nombreAtacado;
        if (hayMonstruos()) {
            System.out.println("Monstruos en la sala:");
            listarMonstruos();
            do {
                nombreAtacado = Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar:");
                atacado = buscarMonstruo(nombreAtacado);
            } while (atacado == null);
        }
        return atacado;
    }

    /**
     * Método buscarMonstruo para buscar un monstruo dado el nombre del mismo
     *
     * @param nombreMonstruo String que contiene el nombre del monstruo que se quiere buscar
     * @return Monstruo buscado si encuentra el nombre / null si no lo encuentra
     */
    public Monstruo buscarMonstruo(String nombreMonstruo) {
        Monstruo buscado = null;
        for (int i = 0; i < this.monstruos.length; i++) {
            if (monstruos[i] != null) {
                if (monstruos[i].getNombre().equals(nombreMonstruo)) buscado = monstruos[i];
            }
        }
        return buscado;
    }

    /**
     * Método listarMonstruos para mostrar por pantalla la información de los monstruos.
     * Muestra por pantalla la información de los monstruos utilizando los métodos implementados en la clase "monstruo"
     */
    private void listarMonstruos() {
        for (int i = 0; i < this.monstruos.length; i++) {
            System.out.println(monstruos[i]);
        }
    }

    /**
     * Método eliminarMonstruo para eliminar un monstruo de la lista segun un nombre dado.
     * Busca en la lista el monstruo según el nombre pasado como parámetro y lo elimina.
     *
     * @param nombreMonstruo nombre del monstruo que se quiere eliminar
     */
    public void eliminarMonstruo(String nombreMonstruo) {
        for (int i = 0; i < this.monstruos.length; i++) {
            if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) monstruos[i] = null;
        }
    }

    /**
     * Método hayTrampas para saber si la sala dispone de alguna trampa
     *
     * @return true si hay alguna trampa / false si no hay trampas
     */
    public boolean hayTrampas() {
        boolean result = false;
        int pos = 0;
        while (pos < trampas.length && !result) {
            if (trampas[pos] == null) pos++;
            else result = true;
        }
        return result;
    }

    /**
     * Método getFila: Devuelve la posición de la fila de la sala dentro del mapa
     *
     * @return int fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método getColumna: Devuelve la posición de la columna de la sala dentro del mapa
     *
     * @return int columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método hayItems para mostrar si existe algún item en la sala
     *
     * @return true si se encuentra al menos un ítem / false si no hay ningún ítem
     */
    public boolean hayItems() {
        boolean result = false;
        int pos = 0;
        while (pos < items.length && !result) {
            if (items[pos] == null) pos++;
            else result = true;
        }
        return result;
    }

    /**
     * Método buscarItem para obtener un item según una descripcion dada
     *
     * @param descripcion String que contiene el nombre del ítem que queremos buscar
     * @return Item result si encuentra el nombre / null si no lo encuentra
     */
    public Item buscarItem(String descripcion) {
        Item result = null;
        for (int i = 0; i < this.items.length; i++) {
            if (items[i] != null && items[i].getDescripcion().equals(descripcion)) result = items[i];
        }
        return result;
    }

    /**
     * Método buscarTrampa para obtener una trampa según una descripcion dada
     *
     * @param descripcion String que contiene la descripción de la trampa que queremos eliminar
     * @return Trampa result si encuentra el nombre / null si no lo encuentra
     */
    public Trampa buscarTrampa(String descripcion) {
        Trampa result = null;
        for (int i = 0; i < this.trampas.length; i++) {
            if (trampas[i].getDescripcion().equals(descripcion)) result = trampas[i];
        }
        return result;
    }

    /**
     * Método getTrampas: Devuelve el vector de trampas de la sala
     *
     * @return Trampa[] trampas
     */
    public Trampa[] getTrampas() {
        return trampas;
    }

    /**
     * Método seleccionarItem para obtener un ítem concreto con parámetros pasados por pantalla.
     * Muestra por pantalla todos los items de la sala para despues pedir que se introduzca una descripcion del
     * ítem que se quiere seleccionar
     *
     * @param teclado objeto de la clase Scanner
     * @return Item result
     */
    public Item seleccionarItem(Scanner teclado) {
        Item result = null;
        String nombreItem = "";
        if (hayItems()) {
            System.out.println("Ítems en la sala:");
            listarItems();

            while (result == null && !(nombreItem.toUpperCase().equals("NINGUNO"))) {
                nombreItem = Utilidades.leerCadena(teclado, "Escribe la descripción del ítem que quieres coger " +
                        "(NINGUNO para cancelar):");
                result = buscarItem(nombreItem);
            }
        }
        return result;
    }

    /**
     * Método listarItems para mostrar por pantalla todos los ítems.
     * Utiliza las funciones de la clase Item para poder mostrar por pantalla toda la información de todos los
     * ítems que hay en la sala
     */
    private void listarItems() {
        for (int i = 0; i < this.items.length; i++) {
            if (items[i] != null) System.out.println(items[i]);
        }
    }

    /**
     * Método eliminarItem para eliminar un ítem con la descripcion pasada como parámetro.
     * Busca el ítem que coincida con la descripción pasada por parámetro y lo elimina de la lista de ítems
     *
     * @param descripcion String que contiene la descripción del ítem que se quiere eliminar
     */
    public void eliminarItem(String descripcion) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getDescripcion().equals(descripcion)) items[i] = null;
        }
    }
}
