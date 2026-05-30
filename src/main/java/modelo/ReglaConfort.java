package modelo;
import java.util.List;

/**
 * Regla que apaga todos los dispositivos cuando hay confort o la casa está vacía.
 * Condición: temperatura entre 20-25°C y luz mayor de 700 lux, o ausencia de presencia.
 * @author Marco Antonio Moreno Rodríguez
 */

public class ReglaConfort implements IRegla {

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor sensorTemp = null;
        Sensor sensorLight = null;
        Sensor sensorPir = null;

        for (Sensor sensor : sensores) {
            if (sensor.getID().equals("temp")) sensorTemp = sensor;
            if (sensor.getID().equals("light")) sensorLight = sensor;
            if (sensor.getID().equals("pir")) sensorPir = sensor;
        }
        if (sensorTemp != null && sensorLight != null && sensorPir != null) {
            if (sensorTemp.isEstado() && sensorLight.isEstado() && sensorPir.isEstado()){
                if ((sensorTemp.getValorActual() > 20 && sensorTemp.getValorActual() < 25 && sensorLight.getValorActual() > 700) || sensorPir.getValorActual() == 0) {
                    for (Actuador actuador : actuadores){
                        if (actuador.getID().equals("fan")){
                            actuador.cambiarEstado("OFF");
                        }
                        if (actuador.getID().equals("bulb")){
                            actuador.cambiarEstado("OFF");
                        }
                    }
                }   
            }
        }
    }
}
