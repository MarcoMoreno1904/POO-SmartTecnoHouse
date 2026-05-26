package modelo;

public abstract class Sensor implements IDispositivo {
    protected String id;
    protected String nombre;
    protected String unidad;
    protected double máximo;
    protected double mínimo;
    protected double valorActual;
    protected boolean estado;

    public Sensor(String id, String nombre, String unidad, double máximo, double mínimo) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.máximo = máximo;
        this.mínimo = mínimo;
        this.valorActual = 0.0;
        this.estado = false;
    }

    // Implementación de los métodos de la interfaz
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
        if (estado) {
            return valorActual + unidad;
        }
        return "Inactivo";
    }

    // Lógica
    public boolean actualizarValor(double nuevoValor) {
        if (nuevoValor >= mínimo && nuevoValor <= máximo) {
            valorActual = nuevoValor;
            this.estado = true;
            return true;
        } else {
            this.estado = false;
            return false;
        }
    }

    public boolean isEstado() {
        return estado;
    }
    public double getValorActual() {
        return valorActual;
    }
}
