package modelo;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase principal del modelo. Representa el sistema domótico completo.
 * Agrupa todos los sensores, actuadores y reglas activas del sistema.
 * Actúa como fachada del modelo para el Controlador.
 * @author Marco Antonio Moreno Rodríguez
 */
public class SmartTecnoHouse {
    private List<Sensor> sensores = new ArrayList<>();
    private List<Actuador> actuadores = new ArrayList<>();
    private List<IRegla> reglas = new ArrayList<>();
    private Logger logger = new Logger();

    /**
     * Inicializa el sistema creando todos los sensores, actuadores y reglas.
     * También inicializa el archivo de log.
     */
    public SmartTecnoHouse() {
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

    /**
     * Ejecuta todas las reglas activas del sistema.
     * Implementa el patrón Strategy iterando sobre la lista de reglas.
     */
    public void ejecutarReglas() {
        for (IRegla regla : reglas) {
            regla.aplicar(sensores, actuadores);
        }
    }

    /**
     * Devuelve la lista completa de sensores del sistema.
     * @return Lista de sensores
     */
    public List<Sensor> getSensores() { return sensores; }

    /**
     * Devuelve la lista completa de actuadores del sistema.
     * @return Lista de actuadores
     */
    public List<Actuador> getActuadores() { return actuadores; }

    /**
     * Busca un sensor por su identificador.
     * @param id Identificador del sensor, por ejemplo "temp"
     * @return El sensor encontrado, o null si no existe
     */
    public Sensor getSensor(String id) {
        for (Sensor sensor : sensores) {
            if (sensor.getID().equals(id)) return sensor;
        }
        return null;
    }

    /**
     * Busca un actuador por su identificador.
     * @param id Identificador del actuador, por ejemplo "fan"
     * @return El actuador encontrado, o null si no existe
     */
    public Actuador getActuador(String id) {
        for (Actuador actuador : actuadores) {
            if (actuador.getID().equals(id)) return actuador;
        }
        return null;
    }

    /**
     * Devuelve el logger del sistema para registrar acciones.
     * @return Instancia del logger
     */
    public Logger getLogger() { return logger; }
}