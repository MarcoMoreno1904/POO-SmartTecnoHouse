import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;
import controlador.Controlador;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            SmartTecnoHouse modelo = new SmartTecnoHouse();
            VentanaPrincipal vista = new VentanaPrincipal(
                modelo.getSensores(),
                modelo.getActuadores()
            );

            Controlador controlador = new Controlador(modelo, vista);

            vista.setControlador(controlador);
            vista.setVisible(true);
        });
    }
}
