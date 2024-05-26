/**
 * Clase Monstruo: Permite crear y gestrionar los monstruos que contienen las salas del juego
 */
public class Monstruo {
    /**
     * Parámetro entero que contiene la cantidad de vida del monstruo
     */
    private int vida;
    /**
     * Parámetro inalterable entero que contiene el ataque del monstruo
     */
    private final int ataque;
    /**
     * Parámetro inalterable entero que contiene la defensa del monstruo
     */
    private final int defensa;
    /**
     * Parámetro inalterable que contiene el nombre del monstruo
     */
    private final String nombre;

    /**
     * Constructor clase Monstruo
     *
     * @param nombre nombre del monstruo
     * @param vida cantidad de vida del monstruo
     * @param ataque cantidad de ataque del monstruo
     * @param defensa cantidad de defensa del monsturo
     */
    public Monstruo(String nombre, int vida, int ataque, int defensa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nombre = nombre;
    }

    /**
     * Método getVida: Devuelve la cantidad de vida del monstruo
     *
     * @return int vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método getAtaque: Devuelve la cantidad de ataque del monstruo
     *
     * @return int ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método getDefensa: Devuelve la cantidad de defensa del monstruo
     *
     * @return int defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método getNombre: Devuelve el nombre del monstruo
     *
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método recibirDanyo para calcular la vida restante
     * Actualiza la vida restante del monstruo despues de un ataque, siempre que el
     * valor de ataque sea positivo
     *
     * @param ataque Valor entero que contiene la cantidad de ataque que recibirá el mosntruo
     */
    public void recibirDanyo(int ataque) {
        if (ataque > 0) {
            if (this.vida < ataque) {
                vida = 0;
            } else this.vida -= ataque;
        }
    }

    /**
     * Método sobreescrito para devolver la información de un monstruo
     * Devuelve un String con la información del monstruo en el siguiente formato: "[ Trasgo (V: 20, A: 5, D: 2) ]"
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[ " + nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ") ]";
    }

    /**
     * Método que sobreescribe el comportamiento de equals
     * Compara si el objeto pasado como parámetro es igual a este, en función delos parámetros internos
     * del objeto (nombre, vida, ataque, defensa)
     *
     * @param obj Objeto de la clase Object
     * @return True en caso de ser igual, false en otro caso
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof Monstruo) {
            if (this.nombre.equals(((Monstruo) obj).getNombre()) && this.vida == ((Monstruo) obj).getVida() &&
                    this.ataque == ((Monstruo) obj).getAtaque() &&
                    this.defensa == ((Monstruo) obj).getDefensa()) equals = true;
        }
        return equals;
    }
}
