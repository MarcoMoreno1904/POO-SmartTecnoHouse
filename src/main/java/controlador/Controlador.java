package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Actuador;
import modelo.Sensor;
import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;

public class Controlador implements ActionListener {

    private SmartTecnoHouse modelo;
    private VentanaPrincipal vista;

    public Controlador(SmartTecnoHouse modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("CARGAR_JSON")) {
            manejarCargaJSON();
        } else if (comando.startsWith("ACTUADOR_")) {
            String idActuador = comando.replace("ACTUADOR_", "");
            manejarCambioActuador(idActuador);
        }
    }

    private void manejarCargaJSON() {
    JFileChooser selector = new JFileChooser();
    selector.setFileFilter(new FileNameExtensionFilter("Archivos JSON", "json"));
    int resultado = selector.showOpenDialog(null);

    if (resultado == JFileChooser.APPROVE_OPTION) {
        String ruta = selector.getSelectedFile().getAbsolutePath();
        vista.setRutaJSON(selector.getSelectedFile().getName());

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea.trim());
            }
            reader.close();

            String texto = contenido.toString();
            String[] bloques = texto.split("\\{");

            for (String bloque : bloques) {
                if (!bloque.contains("sensor_id")) continue;

                String idSensor = extraerValorString(bloque, "sensor_id");
                String valorStr = extraerValorNumerico(bloque, "value");

                if (idSensor != null && valorStr != null) {
                    double valor = Double.parseDouble(valorStr);
                    Sensor sensor = modelo.getSensor(idSensor);
                    if (sensor != null) {
                        sensor.actualizarValor(valor);
                    }
                }
            }

            modelo.ejecutarReglas();
            refrescarVista();
            vista.agregarLog(timestamp() + " Lecturas cargadas correctamente.");

        } catch (Exception ex) {
            vista.agregarLog(timestamp() + " Error al cargar JSON: " + ex.getMessage());
        }
    }
}

private String extraerValorString(String bloque, String campo) {
    String buscar = "\"" + campo + "\"";
    int pos = bloque.indexOf(buscar);
    if (pos == -1) return null;
    int inicio = bloque.indexOf("\"", pos + buscar.length() + 1);
    if (inicio == -1) return null;
    int fin = bloque.indexOf("\"", inicio + 1);
    if (fin == -1) return null;
    return bloque.substring(inicio + 1, fin);
}

private String extraerValorNumerico(String bloque, String campo) {
    String buscar = "\"" + campo + "\"";
    int pos = bloque.indexOf(buscar);
    if (pos == -1) return null;
    int dospuntos = bloque.indexOf(":", pos);
    if (dospuntos == -1) return null;
    String resto = bloque.substring(dospuntos + 1).trim();
    StringBuilder numero = new StringBuilder();
    for (char c : resto.toCharArray()) {
        if (Character.isDigit(c) || c == '.' || c == '-') {
            numero.append(c);
        } else if (numero.length() > 0) {
            break;
        }
    }
    return numero.length() > 0 ? numero.toString() : null;
}

    private void manejarCambioActuador(String idActuador) {
        int indice = buscarIndiceActuador(idActuador);
        if (indice == -1) return;

        String nuevoEstado = vista.getEstadoSeleccionado(indice);
        Actuador actuador = modelo.getActuador(idActuador);

        if (actuador != null) {
            boolean exito = actuador.cambiarEstado(nuevoEstado);
            if (exito) {
                vista.actualizarActuador(indice, nuevoEstado);
                vista.agregarLog(timestamp() + " " + actuador.getNombre() 
                    + " -> " + nuevoEstado + " (MANUAL)");
            } else {
                vista.agregarLog(timestamp() + " Error: estado no válido para " 
                    + actuador.getNombre());
            }
        }
    }

    private void refrescarVista() {
        java.util.List<Sensor> sensores = modelo.getSensores();
        for (int i = 0; i < sensores.size(); i++) {
            vista.actualizarSensor(i, sensores.get(i).getEstadoActual());
        }
        java.util.List<Actuador> actuadores = modelo.getActuadores();
        for (int i = 0; i < actuadores.size(); i++) {
            vista.actualizarActuador(i, actuadores.get(i).getEstadoActual());
        }
    }

    private int buscarIndiceActuador(String idActuador) {
        java.util.List<Actuador> actuadores = modelo.getActuadores();
        for (int i = 0; i < actuadores.size(); i++) {
            if (actuadores.get(i).getID().equals(idActuador)) {
                return i;
            }
        }
        return -1;
    }

    private String timestamp() {
        return LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}