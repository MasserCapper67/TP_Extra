import java.io.*;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Motor
 */
public class Motor {
    Sala[][] mapa;
    private final int maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala;

    /**
     * Constructor Clase Motor
     *
     * @param filas
     * @param columnas
     * @param maxItemsPorSala
     * @param maxMonstruosPorSala
     * @param maxTrampasPorSalas
     */
    public Motor(int filas, int columnas, int maxItemsPorSala, int maxMonstruosPorSala, int maxTrampasPorSalas) {
        this.mapa = new Sala[filas][columnas];
        this.maxItemsPorSala = maxItemsPorSala;
        this.maxMonstruosPorSala = maxMonstruosPorSala;
        this.maxTrampasPorSala = maxTrampasPorSalas;
    }

    /**
     * Clase cargarMapa para construir la matriz de mapa a traves del fichero
     * leer los datos del fichero de mapa pasado por parametro y generar una matriz Sala[][]
     * con dimension Sala[fila][columna] e inicializar la sala con los valores con la descripción del fichero
     * y los parámetros de maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala.
     *
     * @param ficheroMapa
     * @return sala generada
     */
    Sala[][] cargarMapa(String ficheroMapa) {
        Sala[][] mapa = this.mapa;
        BufferedReader bufferedReader = null;
        ficheroMapa = "fichSalas.txt";
        try {
            bufferedReader = new BufferedReader(new FileReader(ficheroMapa));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] campos = line.split(";");
                int campo1 = Integer.parseInt(campos[0]);
                int campo2 = Integer.parseInt(campos[1]);
                String descripcion = campos[2];
                mapa[campo1][campo2] = new Sala(descripcion, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala, campo1, campo2);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichero de mapa no encontrado");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return mapa;
    }

    /**
     * Metodo cargarItems para agregar los items del fichero en el mapa
     * Método para leer un fichero de items pasado por parámetro y según
     * la fila y columna introducir el item en la sala.
     *
     * @param ficheroItems
     */
    private void cargarItems(String ficheroItems) {
        BufferedReader bufferedReader = null;
        ficheroItems = "fichItems.txt";
        try {
            bufferedReader = new BufferedReader(new FileReader(ficheroItems));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] campos = line.split(";");

                int campo1 = Integer.parseInt(campos[0]);
                int campo2 = Integer.parseInt(campos[1]);
                String descripcion = campos[2];
                double peso = Double.parseDouble(campos[3]);
                int valor = Integer.parseInt(campos[4]);

                Item item = new Item(descripcion, peso, valor);
                mapa[campo1][campo2].agregarItem(item);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichero de ítems no encontrado");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método cargarMonstruos para agregar los monstruos del fichero en el mapa
     * Método para leer un fichero de Monstruos pasado por parámetro y según
     * la fila y columna introducir el monstruo en la sala.
     *
     * @param ficheroMonstruos
     */
    private void cargarMonstruos(String ficheroMonstruos) {
        BufferedReader bufferedReader = null;
        ficheroMonstruos = "fichMonstruos.txt";
        try {
            bufferedReader = new BufferedReader(new FileReader(ficheroMonstruos));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] campos = line.split(";");
                int campo1 = Integer.parseInt(campos[0]);
                int campo2 = Integer.parseInt(campos[1]);
                String nombre = campos[2];
                int vida = Integer.parseInt(campos[3]);
                int ataque = Integer.parseInt(campos[4]);
                int defensa = Integer.parseInt(campos[5]);

                Monstruo monstruo = new Monstruo(nombre, vida, ataque, defensa);
                mapa[campo1][campo2].agregarMonstruo(monstruo);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichero de monstruos no encontrado.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método cargarTrampas para agregar las trampas del fichero en el mapa
     * Método para leer un fichero de trampas pasado por parámetro y según
     * la fila y columna introducir la trampa en la sala.
     *
     * @param ficheroTrampas
     */
    private void cargarTrampas(String ficheroTrampas) {
        BufferedReader bufferedReader = null;
        ficheroTrampas = "fichTrampas.txt";
        try {
            bufferedReader = new BufferedReader(new FileReader(ficheroTrampas));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] campos = line.split(";");
                int campo1 = Integer.parseInt(campos[0]);
                int campo2 = Integer.parseInt(campos[1]);
                String descripcion = campos[2];
                int danyo = Integer.parseInt(campos[3]);

                Trampa trampa = new Trampa(descripcion, danyo);
                mapa[campo1][campo2].agregarTrampa(trampa);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichero de trampas no encontrado.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo iniciar, para preparar el mapa
     * instanciación del parametro mapa y carga de datos con los ficheros pasados como parámetros
     *
     * @param ficheroMapa
     * @param ficheroItems
     * @param ficheroMonstruos
     * @param ficheroTrampas
     */
    public void iniciar(String ficheroMapa, String ficheroItems, String ficheroMonstruos, String ficheroTrampas) {
        cargarMapa(ficheroMapa);
        cargarItems(ficheroItems);
        cargarMonstruos(ficheroMonstruos);
        cargarTrampas(ficheroTrampas);
    }

    /**
     * Método getSala para obtener una sala concreta del mapa
     * devolver una Sala concreta del mapa
     *
     * @param fila
     * @param columna
     * @return
     */
    public Sala getSala(int fila, int columna) {
        return mapa[fila][columna];
    }

    /**
     * Método mostrarMapa para transformar el mapa en String
     * construir un String con la información contenida en el mapa
     * respetando el formato que aparece en la memoria de la práctica
     *
     * @param fila
     * @param columna
     * @return
     */
    public String mostrarMapa(int fila, int columna) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔");
        for (int j = 0; j < mapa[0].length; j++) {
            sb.append("=");
        }
        sb.append("╗");
        sb.append("\n");
        for (int i = 0; i < mapa.length; i++) {
            sb.append("║");
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] != null) {
                    if (i == fila && j == columna) {
                        sb.append("@");
                    } else sb.append("░");
                } else sb.append(" ");
            }
            sb.append("║\n");
        }
        sb.append("╚");
        for (int j = 0; j < mapa[0].length; j++) {
            sb.append("=");
        }
        sb.append("╝");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Método jugar para empezar a jugar con el personaje
     * método complejo en el que hay que seguir la siguiente ejecución:
     * 1. mostrar el mapa por pantalla
     * 2. Obtener la sala actual y mientras el personaje tenga vida y no haya llegado a la casilla final
     * 3. Durante una jugada mostrar la descripcion de la sala actual
     * 4. Comprobar si hay monstruos en la sala y si es así entrar en combate
     * 4.a El combate acaba cuando la vida del monstruo o la vida del personaje llega a 0
     * 4.b cada turno en el combate el personaje ataca al monstruo y restamos su vida
     * 4.c Si la vida no llega a 0 el monstruo hace daño al personaje
     * 5. Las salas pueden tener trampas
     * 5.a Si hay trampa hay que comprobar si un valor aleatorio entre 1 y 50 es inferior a la destreza del personaje, si es asi esquiva la trampa
     * 5.b Si no esquiva la trampa el personaje recibe daño
     * 5.c al igual que en combate hay que tener en cuenta si la vida del personaje lleva a 0
     * 6. Por último puede haber items en la sala, en cuyo caso habrá que preguntar al usuario qué ítems quiere guardarse (o NINGUNO para terminar)
     * ¡IMPORTANTE! se debe mostrar por pantalla avisos para cada opción dando feedback al usuario de todo lo que ocurra (consultar enunciado)
     *
     * @param teclado
     * @param personaje
     * @param random
     */
    public void jugar(Scanner teclado, Personaje personaje, Random random) {
        int filaActual = 0;
        int columnaActual = 0;
        boolean muerto = false;
        boolean victoria = false;
        boolean pararObjetos = false;
        random = new Random();
        Sala salaActual = mapa[filaActual][columnaActual];
        Sala salaFinal = mapa[mapa.length - 1][mapa[0].length - 1];

        while (!muerto && !victoria) {
            mostrarMapa(filaActual, columnaActual);
            System.out.println(salaActual.getDescripcion());

            if (salaActual.hayTrampas()) {
                Trampa[] trampas = salaActual.getTrampas();
                for (int i = 0; i < trampas.length; i++) {
                    if (trampas[i] != null) {
                        int precisionTrampa = random.nextInt(50) + 1;
                        if (precisionTrampa > personaje.getDestreza()) {
                            System.out.println("¡Eres tomado por sorpresa por la trampa " + trampas[i].getDescripcion()
                                    + "!\nRecibes " + trampas[i].getDanyo() + " puntos de daño...");
                            personaje.recibirDanyo(trampas[i].getDanyo());
                            if (personaje.getVida() <= 0) muerto = true;
                        } else {
                            System.out.println("¡Esquivaste la trampa " + trampas[i].getDescripcion() + "!");
                        }
                    }
                }
            }

            while (salaActual.hayMonstruos() && !muerto) {
                Monstruo monstruoAtacado = salaActual.seleccionarMonstruo(teclado);
                while (personaje.getVida() > 0 && monstruoAtacado.getVida() > 0) {
                    System.out.println("¡Atacas a " + monstruoAtacado.getNombre() + "!");
                    monstruoAtacado.recibirDanyo(personaje.getAtaque());
                    if (monstruoAtacado.getVida() > 0) {
                        System.out.println("¡" + monstruoAtacado.getNombre() + " ataca a " + personaje.getNombre() + "!");
                        personaje.recibirDanyo(monstruoAtacado.getAtaque());
                    }
                }
                if (personaje.getVida() <= 0) muerto = true;
                else {
                    System.out.println("¡Derrotaste a " + monstruoAtacado.getNombre() + "!");
                    salaActual.eliminarMonstruo(monstruoAtacado.getNombre());
                }
            }

            while (!pararObjetos && !muerto) {
                Item itemSeleccionado = salaActual.seleccionarItem(teclado);
                if (itemSeleccionado != null) {
                    if (!personaje.anyadirItem(itemSeleccionado)) {
                        System.out.println("Peso máximo alcanzado. No puedes recoger más objetos.");
                    } else {
                        salaActual.eliminarItem(itemSeleccionado.getDescripcion());
                    }
                } else pararObjetos = true;
                if (pararObjetos) System.out.println(personaje.infoMochila());
            }
            pararObjetos = false;

            if (salaActual == salaFinal) victoria = true;

            if (!muerto && !victoria) {
                mostrarMapa(filaActual, columnaActual);
                salaActual = seleccionarMovimiento(teclado, salaActual);
                filaActual = salaActual.getFila();
                columnaActual = salaActual.getColumna();
            }
        }

        if (muerto) {
            System.out.println("Moriste. Fin de la partida,");
            System.out.println("Valor total de los objetos de la mochila de " + personaje.getNombre() + ": " +
                    personaje.getValorMochila());
        }
        if (victoria) {
            System.out.println("¡Enhorabuena! Has completado el juego");
            System.out.println("Valor total de los objetos de la mochila de " + personaje.getNombre() + ": " +
                    personaje.getValorMochila());
        }
    }

    /**
     * Metodo seleccionarMovimiento para establecer las acciones que tome el jugador con su personaje
     * El desplazamiento del personaje se entiende como norte (N), sur (S), este (E) u oeste (O)
     * en este método hay que capturar por pantalla la acción que va a tomar el usuario de entre las posibles
     * para ello hay que tener en cuenta que se debe avisar al usuario si puede realizar o no la acción.
     * Se devolverá la sala destino a la que se ha movido el personaje.
     *
     * @param teclado
     * @param salaActual
     * @return
     */
    public Sala seleccionarMovimiento(Scanner teclado, Sala salaActual) {
        Sala salaDestino = null;
        boolean movimientoValido = false;

        do {
            String movimiento = Utilidades.leerMovimiento(teclado, "Introduce el movimiento (N, E, S, O):");
            switch (movimiento) {
                case "N":
                    if (salaActual.getFila() - 1 >= 0 && salaActual.getFila() - 1 < mapa.length) {
                        if (mapa[salaActual.getFila() - 1][salaActual.getColumna()] != null) {
                            movimientoValido = true;
                        }
                        if (movimientoValido) {
                            salaDestino = mapa[salaActual.getFila() - 1][salaActual.getColumna()];
                            //salaActual = salaDestino;
                        } else System.out.println("Camino cerrado por esa dirección.");
                    }
                    break;
                case "E":
                    if (salaActual.getColumna() + 1 >= 0 && salaActual.getColumna() + 1 < mapa[0].length) {
                        if (mapa[salaActual.getFila()][salaActual.getColumna() + 1] != null) {
                            movimientoValido = true;
                        }
                        if (movimientoValido) {
                            salaDestino = mapa[salaActual.getFila()][salaActual.getColumna() + 1];
                            //salaActual = salaDestino;
                        } else System.out.println("Camino cerrado por esa dirección.");
                    }
                    break;
                case "S":
                    if (salaActual.getFila() + 1 >= 0 && salaActual.getFila() + 1 < mapa.length) {
                        if (mapa[salaActual.getFila() + 1][salaActual.getColumna()] != null) {
                            movimientoValido = true;
                        }
                        if (movimientoValido) {
                            salaDestino = mapa[salaActual.getFila() + 1][salaActual.getColumna()];
                            //salaActual = salaDestino;
                        } else System.out.println("Camino cerrado por esa dirección.");
                    }
                    break;
                case "O":
                    if (salaActual.getColumna() - 1 >= 0 && salaActual.getColumna() - 1 < mapa[0].length) {
                        if (mapa[salaActual.getFila()][salaActual.getColumna() - 1] != null) {
                            movimientoValido = true;
                        }
                        if (movimientoValido) {
                            salaDestino = mapa[salaActual.getFila()][salaActual.getColumna() - 1];
                            //salaActual = salaDestino;
                        } else System.out.println("Camino cerrado por esa dirección.");
                    }
            }
        } while (!movimientoValido);
        return salaDestino;
    }
}
