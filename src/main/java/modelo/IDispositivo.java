package modelo;

/**
 * Contrato común para todos los dispositivos del sistema domótico.
 * Cualquier sensor o actuador debe implementar esta interfaz.
 * @author Marco Antonio Moreno Rodríguez
 */
public interface IDispositivo {

    /**
     * Devuelve el identificador único del dispositivo.
     * @return ID del dispositivo, por ejemplo "temp" o "bulb"
     */
    String getID();

    /**
     * Devuelve el nombre legible del dispositivo.
     * @return Nombre del dispositivo, por ejemplo "Sensor de Temperatura"
     */
    String getNombre();

    /**
     * Devuelve un resumen del estado actual del dispositivo.
     * @return Estado actual, por ejemplo "23.4 °C" o "ON"
     */
    String getEstadoActual();
}
