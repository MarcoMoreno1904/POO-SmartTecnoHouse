package modelo;
import java.util.List;

public class ReglaVentilacion implements IRegla {

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor sensorTemp = null;
        Sensor sensorPir = null;

        for (Sensor sensor : sensores) {
            if (sensor.getID().equals("temp")) sensorTemp = sensor;
            if (sensor.getID().equals("pir")) sensorPir = sensor;
        }
        
        if (sensorTemp != null && sensorPir != null) {
            if (sensorTemp.isEstado() && sensorPir.isEstado()) {
                if (sensorTemp.getValorActual() > 28 && sensorPir.getValorActual() == 1) {

                for (Actuador actuadorFan : actuadores) {
                    if (actuadorFan.getID().equals("fan")) {
                        actuadorFan.cambiarEstado("HIGH");
                    }
                }
                }
            }
        }
    }
}

