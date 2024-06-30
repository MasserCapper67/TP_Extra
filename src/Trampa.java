/**
 * Clase Trampa: Permite crear y gestrionar las distintas trampas que contienen las salas del juego
 *
 * @author Álvaro Bardají Robles
 * @author Sergio Arias
 * @version 1.0
 */
public class Trampa {
    /**
     * Atributo que guarda el nombre / descripción de la trampa
     */
    private final String descripcion;
    /**
     * Atributo que indica la cantidad de daño que efectúa la trampa
     */
    private final int danyo;

    /**
     * Constructor de la clase Trampa
     * @param descripcion nombre de la trampa
     * @param danyo cantidad de daño que efectúa la trampa
     */
    public Trampa(String descripcion, int danyo) {
        this.descripcion = descripcion;
        this.danyo = danyo;
    }

    /**
     * Método getDescripcion: devuelve el nombre / descripción de la trampa
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método getDanyo: Devuelve la cantidad de daño que efectúa la trampa
     * @return int danyo
     */
    public int getDanyo() {
        return danyo;
    }

    // HE AÑADIDO UN PUBLIC BOOLEAN EQUALS (OBJECT OBJ) PARA QUE SE COMPAREN ATRIBUTOS Y NO REFERENCIAS EN AGREGARTRAMPA
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof Trampa){
            if (this.descripcion.equals(((Trampa) obj).getDescripcion()) && this.danyo == ((Trampa) obj).getDanyo()){
                equals = true;
            }
        }
        return equals;
    }
}
