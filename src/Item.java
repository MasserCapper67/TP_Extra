/**
 * Clase Item: Permite crear y gestrionar los ítems que contienen las salas del juego
 *
 * @author Álvaro Bardají Robles
 * @author Sergio Arias
 * @version 1.0
 */
public class Item {
    /**
     * Valor del peso del objeto
     */
    private final double peso;
    /**
     * Cantidad de valor que posee el objeto
     */
    private final double valor;
    /**
     * Nombre / descripción del objeto
     */
    private final String descripcion;

    /**
     * Constructor de la clase item
     *
     * @param descripcion Nombre / descripción del objeto
     * @param peso Peso del objeto
     * @param valor Valor del objeto
     */
    public Item(String descripcion, double peso, double valor) {
        this.descripcion = descripcion;
        this.peso = peso;
        this.valor = valor;
    }

    /**
     * Método getPeso: Devuelve el peso del objeto
     *
     * @return double peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Método getValor: Devuelve el valor del objeto
     *
     * @return double valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * Método getDescripcion: Devuelve la descripción del objeto
     *
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método sobreescrito para devolver la información de un ítem
     *
     * @return String
     */
    @Override
    public String toString() {
        return descripcion + " (Peso: " + peso + ", Valor: " + valor + ")";
    }

    /**
     * Método que sobreescribe el comportamiento de equals.
     * Compara si el objeto pasado como parámetro es igual a este, en función de sus parámetros
     *
     * @param obj
     * @return True en caso de ser igual, false en otro caso
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof Item) {
            if (this.peso == ((Item) obj).getPeso() && this.valor == ((Item) obj).getValor() &&
                    this.descripcion.equals(((Item) obj).getDescripcion())) equals = true;
        }
        return equals;
    }
}
