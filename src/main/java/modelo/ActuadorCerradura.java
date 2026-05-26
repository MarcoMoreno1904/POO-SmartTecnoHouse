package modelo;

public class ActuadorCerradura extends Actuador {

    public ActuadorCerradura() {
        super("lock", "Cerradura", new String[]{"LOCKED", "UNLOCKED"});
    }
}
