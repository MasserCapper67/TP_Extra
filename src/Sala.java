import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase Sala
 */
public class Sala {
    private final String descripcion;
    private final Item[] items;
    private final Monstruo[] monstruos;
    private final Trampa[] trampas;

    private final int fila;
    private final int columna;

    /**
     * Constructor de clase para inicializar los atributos de clase
     *
     * @param descripcion
     * @param max_items
     * @param max_monstruos
     * @param maxTrampasPorSala
     * @param fila
     * @param columna
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
     * Método agregarItem para incluir items en la sala
     * comprobar si existe el objeto en la sala o si la lista de items no está ya llena en caso afirmativo
     * devolver false. En caso de no existir incluirlo en la lista y devolver true
     *
     * @param item
     * @return
     */
    public boolean agregarItem(Item item) {
        boolean agregado = false;
        boolean repetido = false;

        for (int i = 0; i < this.items.length; i++) {
            if (items[i] != null && items[i].equals(item)) repetido = true;
        }
        if (!repetido) {
            int pos = 0;
            while (items[pos] != null && pos < items.length) {
                pos++;
            }
            if (items[pos] == null) {
                items[pos] = item;
                agregado = true;
            }
        }
        return agregado;
    }

    /**
     * Método agregarMonstruo para incluir un monstruo en la sala
     * comprobar si existe el monstruo en la sala o si la lista de monstruos no está ya llena en caso afirmativo
     * devolver false. En caso de no existir incluirlo en la lista y devolver true
     *
     * @param monstruo
     * @return
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        boolean agregado = false;
        boolean repetido = false;

        for (int i = 0; i < this.monstruos.length; i++) {
            if (monstruos[i] != null && monstruos[i].equals(monstruo)) repetido = true;
        }
        if (!repetido) {
            int pos = 0;
            while (monstruos[pos] != null && pos < monstruos.length) {
                pos++;
            }
            if (monstruos[pos] == null) {
                monstruos[pos] = monstruo;
                agregado = true;
            }
        }
        return agregado;
    }

    /**
     * Método agregarTrampa para incluir una trampa en la sala
     * comprobar si existe la trampa en la sala o si la lista de trampas no está ya llena en caso afirmativo
     * devolver false. En caso de no existir incluirlo en la lista y devolver true
     *
     * @param trampa
     * @return
     */
    public boolean agregarTrampa(Trampa trampa) {
        boolean agregado = false;
        boolean repetido = false;

        for (int i = 0; i < this.trampas.length; i++) {
            if (trampas[i] != null && trampas[i].equals(trampa)) repetido = true;
        }
        if (!repetido) {
            int pos = 0;
            while (trampas[pos] != null && pos < trampas.length) {
                pos++;
            }
            if (trampas[pos] == null) {
                trampas[pos] = trampa;
                agregado = true;
            }
        }
        return agregado;
    }

    /**
     * Método getDescripcion
     *
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método hayMonstruos para comprobar si hay algún monstruo en la sala
     * comprobar si hay algún monstruo en la lista
     *
     * @return
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
     * Método seleccionarMonstruo para introducir desde pantalla el nombre de un monstruo
     * Mostrar por pantalla todos los monstruos y luego solicitar que se introduzca el nombre del monstruo que se
     * quiere seleccionar.
     *
     * @param teclado
     * @return
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) {
        Monstruo atacado = null;
        String nombreAtacado;
        if (hayMonstruos()) {
            System.out.println("Monstruos en la sala:");
            listarMonstruos();
            do {
                nombreAtacado = Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar:");
            } while (buscarMonstruo(nombreAtacado) == null);
            //TODO: Recorre dos veces
            atacado = buscarMonstruo(nombreAtacado);
        }
        return atacado;
    }

    /**
     * Método buscarMonstruo para buscar un monstruo dado el nombre del mismo
     * devolver el monstruo según el nombre pasado como parámetro o devolver null si no se encuentra
     *
     * @param nombreMonstruo
     * @return
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
     * Método listarMonstruos para mostrar por pantalla la información de los monstruos
     * mostrar por pantalla la info de los monstruos utilizando los métodos implementados en la clase "monstruo"
     */
    private void listarMonstruos() {
        for (int i = 0; i < this.monstruos.length; i++) {
            System.out.println(monstruos[i]);
        }
    }

    /**
     * Método eliminarMonstruo para eliminar un monstruo de la lista segun un nombre dado
     * buscar en la lista el monstruo segun el nombre pasado como parámetro y eliminarlo.
     *
     * @param nombreMonstruo
     */
    public void eliminarMonstruo(String nombreMonstruo) {
        for (int i = 0; i < this.monstruos.length; i++) {
            if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) monstruos[i] = null;
        }
        /*
        if (hayMonstruos()) {
            Monstruo eliminado = this.buscarMonstruo(nombreMonstruo);
            if (eliminado != null) {
                eliminado = null;
            }
        }
        */
    }

    /**
     * Método hayTrampas para saber si la sala dispone de alguna trampa
     * mostrar si existe alguna trampa en la sala, false en caso contrario
     *
     * @return
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
     * Método getFila
     *
     * @return int fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método getColumna
     *
     * @return int columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método hayItems para mostrar si existe algún item en la sala
     * buscar si hay algún item en la lista de items, false en caso contrario
     *
     * @return
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
     * buscar en la lista de items un item con la descripción pasada como parámetro, devolver null si no lo
     * encuentra
     *
     * @param descripcion
     * @return
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
     * buscar en la lista de trampas una trampa con la descripción pasada como parámetro, devolver null si no lo
     * encuentra
     *
     * @param descripcion
     * @return
     */
    public Trampa buscarTrampa(String descripcion) {
        Trampa result = null;
        for (int i = 0; i < this.trampas.length; i++) {
            if (trampas[i].getDescripcion().equals(descripcion)) result = trampas[i];
        }
        return result;
    }

    /**
     * Método getTrampas
     *
     * @return Trampa[] trampas
     */
    public Trampa[] getTrampas() {
        return trampas;
    }

    /**
     * Método seleccionarItem para obtener un item concreto con parámetro pasados por pantalla
     * Mostrar por pantalla todos los items de la sala para despues pedir que se introduzca una descripcion del
     * item que se quiere seleccionar
     *
     * @param teclado
     * @return
     */
    public Item seleccionarItem(Scanner teclado) {
        Item result = null;
        String nombreItem;
        if (hayItems()) {
            System.out.println("Ítems en la sala:");
            listarItems();
            do {
                teclado.nextLine();
                nombreItem = Utilidades.leerCadena(teclado, "Escribe la descripción del ítem que quieres coger " +
                        "(NINGUNO para cancelar):");
            } while (buscarItem(nombreItem) == null && !(nombreItem.toUpperCase().equals("NINGUNO")));
            result = buscarItem(nombreItem);
        }
        return result;
    }

    /**
     * Método listarItems para mostrar por pantalla todos los items
     * utilizar las funciones de la clase Item para poder mostrar por pantalla toda la información de todos los
     * items que hay en la sala
     */
    private void listarItems() {
        for (int i = 0; i < this.items.length; i++) {
            if (items[i] != null) System.out.println(items[i]);
        }
    }

    /**
     * Método eliminarItem para eliminar un item con la descripcion pasada como parámetro
     * buscar el item que coincida con la descripción pasada por parámetro y eliminarlo de la lista de items
     *
     * @param descripcion
     */
    public void eliminarItem(String descripcion) {
        if (hayItems()) {
            Item eliminado = this.buscarItem(descripcion);
            if (eliminado != null) {
                eliminado = null;
            }
        }
    }
}
