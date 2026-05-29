package modelo;
import java.util.List;
import java.util.ArrayList;

public class SmartTecnoHouse {
    private List<Sensor> sensores = new ArrayList<>();
    private List<Actuador> actuadores = new ArrayList<>();
    private List<IRegla> reglas = new ArrayList<>();
    private Logger logger = new Logger();

    public SmartTecnoHouse(){
        sensores.add(new SensorTemperatura());
        sensores.add(new SensorLuz());
        sensores.add(new SensorPresencia());
        sensores.add(new SensorHumedad());

        actuadores.add(new ActuadorVentilador());
        actuadores.add(new ActuadorBombilla());
        actuadores.add(new ActuadorCerradura());

        reglas.add(new ReglaConfort());
        reglas.add(new ReglaVentilacion());
        reglas.add(new ReglaIluminacion());
        reglas.add(new ReglaCerradura());

        logger.inicializarLog();
    }

    public void ejecutarReglas(){
        for (IRegla regla : reglas){
            regla.aplicar(sensores, actuadores);
        }
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public List<Actuador> getActuadores() {
        return actuadores;
    }

    public Sensor getSensor(String id) {
        for (Sensor sensor : sensores) {
            if (sensor.getID().equals(id)){
                return sensor;
            }
        }
        return null;
    }

    public Actuador getActuador(String id) {
        for (Actuador actuador : actuadores) {
            if (actuador.getID().equals(id)){
                return actuador;
            }
        }
        return null;
    }

    public Logger getLogger() {
        return logger;
    }
}
