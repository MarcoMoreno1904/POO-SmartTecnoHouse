package modelo;

public class ActuadorVentilador extends Actuador {

    public ActuadorVentilador() {
        super("fan", "Ventilador", new String[]{"OFF", "LOW", "MED", "HIGH"});
    } 
}
