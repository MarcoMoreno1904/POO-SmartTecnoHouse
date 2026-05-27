package modelo;
import java.util.List;

public interface IRegla {
    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
}
