package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String RUTA_LOG = "actuadores.log";
    private static final DateTimeFormatter FORMATO = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void inicializarLog() {
        try {
            FileWriter fw = new FileWriter(RUTA_LOG, false);
            fw.write("TIMESTAMP - ACTUADOR - ESTADO - ORIGEN\n");
            fw.write("==================================================\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al inicializar el log: " + e.getMessage());
        }
    }

    public void registrarAccion(String nombreActuador, String estado, String origen) {
        try {
            FileWriter fw = new FileWriter(RUTA_LOG, true);
            String timestamp = LocalDateTime.now().format(FORMATO);
            fw.write(timestamp + " - " + nombreActuador + " -> " + estado + " (" + origen + ")\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}
