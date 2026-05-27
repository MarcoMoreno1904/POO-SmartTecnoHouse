package modelo;
import java.util.List;

public class ReglaIluminacion implements IRegla{

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor sensorLight = null;
        Sensor sensorPir = null;

        for (Sensor sensor : sensores) {
            if (sensor.getID().equals("light")) sensorLight = sensor;
            if (sensor.getID().equals("pir")) sensorPir = sensor;
        }
        
        if (sensorLight != null && sensorPir != null) {
            if (sensorLight.isEstado() && sensorPir.isEstado()) {
                if (sensorLight.getValorActual() < 300 && sensorPir.getValorActual() == 1) {

                for (Actuador actuador : actuadores) {
                    if (actuador.getID().equals("bulb")) {
                        actuador.cambiarEstado("ON");
                    }
                }
                }
            }
        }
    }
}
