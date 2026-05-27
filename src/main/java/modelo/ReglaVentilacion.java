package modelo;
import java.util.List;

public class ReglaVentilacion implements IRegla {

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor sensorTemp = null;
        Sensor sensorPir = null;

        for (Sensor sensorT : sensores) {
            if (sensorT.getID().equals("temp")) sensorTemp = sensorT;
            if (sensorT.getID().equals("pir")) sensorPir = sensorT;
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

