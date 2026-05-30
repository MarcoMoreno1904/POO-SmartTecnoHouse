package modelo;

/**
 * Clase base abstracta para todos los actuadores del sistema.
 * Gestiona los estados posibles y el estado actual del dispositivo.
 * @author Marco Antonio Moreno Rodríguez
 */
public abstract class Actuador implements IDispositivo {
    protected String id;
    protected String nombre;
    protected String[] estadosPosibles;
    protected String estadoActual;

    /**
     * Constructor base para todos los actuadores.
     * El estado inicial es siempre el primero del array de estados posibles.
     * @param id Identificador único del actuador
     * @param nombre Nombre legible del actuador
     * @param estadosPosibles Array con todos los estados válidos
     */
    public Actuador(String id, String nombre, String[] estadosPosibles) {
        this.id = id;
        this.nombre = nombre;
        this.estadosPosibles = estadosPosibles;
        this.estadoActual = estadosPosibles[0];
    }

    @Override
    public String getID() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getEstadoActual() { return estadoActual; }

    /**
     * Cambia el estado del actuador si el nuevo estado es válido.
     * @param nuevoEstado Estado al que se quiere cambiar
     * @return true si el cambio fue exitoso, false si el estado no es válido
     */
    public boolean cambiarEstado(String nuevoEstado) {
        for (String estado : estadosPosibles) {
            if (estado.equals(nuevoEstado)) {
                estadoActual = nuevoEstado;
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve los estados posibles del actuador.
     * @return Array con todos los estados válidos
     */
    public String[] getEstadosPosibles() { return estadosPosibles; }
}