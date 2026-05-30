package modelo;
import java.util.List;

/**
 * Regla que gestiona el bloqueo automático de la cerradura según la presencia.
 * Bloquea cuando no hay nadie en casa y desbloquea cuando hay presencia.
 * @author Marco Antonio Moreno Rodríguez
 */

public class ReglaCerradura implements IRegla {

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor sensorPir = null;

        for (Sensor sensor : sensores) {
            if (sensor.getID().equals("pir")) sensorPir = sensor;
        }

        if (sensorPir != null) {
            if (sensorPir.isEstado()) {
                if (sensorPir.getValorActual() == 0) {

                for (Actuador actuador : actuadores) {
                    if (actuador.getID().equals("lock")) {
                        actuador.cambiarEstado("LOCKED");
                    }
                }
                } else if (sensorPir.getValorActual() == 1) {
                    for (Actuador actuador : actuadores) {
                        if (actuador.getID().equals("lock")) {
                            actuador.cambiarEstado("UNLOCKED");
                        }
                    }
                }
            }
        }
    }
}
