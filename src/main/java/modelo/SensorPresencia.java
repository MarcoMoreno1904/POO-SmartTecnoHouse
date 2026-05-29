package modelo;

public class SensorPresencia extends Sensor {

    public SensorPresencia() {
        super("pir", "Sensor de Presencia", "boolean", 1, 0);
    }
   @Override
    public String getEstadoActual() {
    if (!isEstado()) return "Inactivo";
    return getValorActual() == 1.0 ? "Detectado" : "No detectado";
}
}
