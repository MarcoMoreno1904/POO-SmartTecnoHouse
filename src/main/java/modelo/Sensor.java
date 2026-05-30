package modelo;

/**
 * Clase base abstracta para todos los sensores del sistema.
 * Contiene la lógica común: unidad de medida, rango válido y último valor leído.
 * @author Marco Antonio Moreno Rodríguez
 */
public abstract class Sensor implements IDispositivo {
    protected String id;
    protected String nombre;
    protected String unidad;
    protected double máximo;
    protected double mínimo;
    protected double valorActual;
    protected boolean estado;

    /**
     * Constructor base para todos los sensores.
     * @param id Identificador único del sensor
     * @param nombre Nombre legible del sensor
     * @param unidad Unidad de medida
     * @param máximo Valor máximo válido
     * @param mínimo Valor mínimo válido
     */
    public Sensor(String id, String nombre, String unidad, double máximo, double mínimo) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.máximo = máximo;
        this.mínimo = mínimo;
        this.valorActual = 0.0;
        this.estado = false;
    }

    @Override
    public String getID() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getEstadoActual() {
        if (estado) return valorActual + " " + unidad;
        return "Inactivo";
    }

    /**
     * Valida y guarda un nuevo valor leído por el sensor.
     * @param nuevoValor Valor a registrar
     * @return true si el valor era válido y se guardó, false si estaba fuera de rango
     */
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

    /**
     * Devuelve el último valor numérico leído por el sensor.
     * @return Último valor registrado
     */
    public double getValorActual() { return valorActual; }

    /**
     * Indica si el sensor ha recibido al menos una lectura.
     * @return true si tiene lectura, false si está inactivo
     */
    public boolean isEstado() { return estado; }
}