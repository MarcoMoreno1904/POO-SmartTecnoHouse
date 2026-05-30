package modelo;

import java.util.List;

/**
 * Interfaz del patrón Strategy para las reglas de negocio del sistema.
 * Cada regla encapsula una condición y una acción sobre los actuadores.
 * @author Marco Antonio Moreno Rodríguez
 */
public interface IRegla {

    /**
     * Evalúa la condición de la regla y actúa sobre los actuadores si se cumple.
     * @param sensores Lista de sensores con sus valores actuales
     * @param actuadores Lista de actuadores que pueden ser modificados
     */
    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
}