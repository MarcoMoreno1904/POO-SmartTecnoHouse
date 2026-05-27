package modelo;

public abstract class Actuador implements IDispositivo {
    protected String id;
    protected String nombre;
    protected String[] estadosPosibles;
    protected String estadoActual;

    public Actuador(String id, String nombre, String[] estadosPosibles) {
        this.id = id;
        this.nombre = nombre;
        this.estadosPosibles = estadosPosibles;
        this.estadoActual = estadosPosibles[0]; // Estado inicial por defecto
    }

    @Override
    public String getID() {
        return id;
    }   
    @Override
    public String getNombre() {
        return nombre;
    }
    @Override
    public String getEstadoActual() {
        return estadoActual;
    }

    public boolean cambiarEstado(String nuevoEstado) {
        for (String estado : estadosPosibles) {
            if (estado.equals(nuevoEstado)) {
                estadoActual = nuevoEstado;
                return true;
            }
        }
        return false;
    }

    public String[] getEstadosPosibles() {
        return estadosPosibles;
    }

}
