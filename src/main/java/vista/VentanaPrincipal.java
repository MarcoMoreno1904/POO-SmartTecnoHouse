package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Sensor;
import modelo.Actuador;

public class VentanaPrincipal extends JFrame {

    private JLabel[] etiquetasSensores;
    private JLabel[] etiquetasActuadores;
    private JComboBox<String>[] comboEstados;
    private JButton[] botonesAplicar;
    private JButton botonCargarJSON;
    private JLabel etiquetaRutaJSON;
    private JTextArea areaLog;

    public VentanaPrincipal(List<Sensor> sensores, List<Actuador> actuadores) {
        setTitle("Smart TecnoHouse - Sistema Domótico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(construirPanelSensores(sensores), BorderLayout.NORTH);
        add(construirPanelActuadores(actuadores), BorderLayout.CENTER);
        add(construirPanelLog(), BorderLayout.SOUTH);
    }

    private JPanel construirPanelSensores(List<Sensor> sensores) {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Estado de Sensores"));
        panel.setLayout(new GridLayout(sensores.size(), 2, 5, 5));
        etiquetasSensores = new JLabel[sensores.size()];

        for (int i = 0; i < sensores.size(); i++) {
            Sensor s = sensores.get(i);
            panel.add(new JLabel(s.getNombre() + ":"));
            etiquetasSensores[i] = new JLabel(s.getEstadoActual());
            panel.add(etiquetasSensores[i]);
        }
        return panel;
    }

    @SuppressWarnings("unchecked")
    private JPanel construirPanelActuadores(List<Actuador> actuadores) {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Control de Actuadores"));
        panel.setLayout(new GridLayout(actuadores.size() + 1, 4, 5, 5));

        panel.add(new JLabel("Dispositivo"));
        panel.add(new JLabel("Estado actual"));
        panel.add(new JLabel("Nuevo estado"));
        panel.add(new JLabel(""));

        etiquetasActuadores = new JLabel[actuadores.size()];
        comboEstados = new JComboBox[actuadores.size()];
        botonesAplicar = new JButton[actuadores.size()];

        for (int i = 0; i < actuadores.size(); i++) {
            Actuador a = actuadores.get(i);
            panel.add(new JLabel(a.getNombre()));
            etiquetasActuadores[i] = new JLabel(a.getEstadoActual());
            panel.add(etiquetasActuadores[i]);
            comboEstados[i] = new JComboBox<>(a.getEstadosPosibles());
            panel.add(comboEstados[i]);
            botonesAplicar[i] = new JButton("Aplicar");
            botonesAplicar[i].setActionCommand("ACTUADOR_" + a.getID());
            panel.add(botonesAplicar[i]);
        }

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonCargarJSON = new JButton("Cargar lecturas JSON");
        botonCargarJSON.setActionCommand("CARGAR_JSON");
        etiquetaRutaJSON = new JLabel("Ningún archivo cargado");
        panelInferior.add(botonCargarJSON);
        panelInferior.add(etiquetaRutaJSON);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(panel, BorderLayout.CENTER);
        panelCentral.add(panelInferior, BorderLayout.SOUTH);
        return panelCentral;
    }

    private JPanel construirPanelLog() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Registro de actividad"));
        panel.setPreferredSize(new Dimension(700, 150));
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        panel.add(new JScrollPane(areaLog), BorderLayout.CENTER);
        return panel;
    }

    public void setControlador(ActionListener controlador) {
        for (JButton btn : botonesAplicar) {
            btn.addActionListener(controlador);
        }
        botonCargarJSON.addActionListener(controlador);
    }

    public void actualizarSensor(int indice, String valor) {
        etiquetasSensores[indice].setText(valor);
    }

    public void actualizarActuador(int indice, String estado) {
        etiquetasActuadores[indice].setText(estado);
    }

    public String getEstadoSeleccionado(int indice) {
        return (String) comboEstados[indice].getSelectedItem();
    }

    public void agregarLog(String mensaje) {
        areaLog.append(mensaje + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }

    public void setRutaJSON(String ruta) {
        etiquetaRutaJSON.setText(ruta);
    }
}
