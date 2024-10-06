import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                HashMap<String, Actividad> mapaActividades = new HashMap<>();
                Data.leerDataActividad(mapaActividades);
                
                // Inicializar el Stack para guardar los cambios
                Stack<String> cambios = new Stack<>();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
                Ventana v = new Ventana(mapaActividades, reader, cambios);
                v.setVisible(true);
                
                // Agregar un shutdown hook para guardar los cambios al cerrar la aplicación
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        guardarCambios(cambios);
                    } catch (IOException e) {
                        System.err.println("Error al guardar los cambios: " + e.getMessage());
                    }
                }));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer los datos: " + e.getMessage());
            } catch (CapacidadMaximaExcedidaException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FechaInvalidaException ez) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ez);
            }
        });
    }
    
    private static void guardarCambios(Stack<String> cambios) throws IOException {
        if (!cambios.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("cambios.txt"))) {
                writer.write("Registro de cambios:\n\n");
                while (!cambios.isEmpty()) {
                    writer.write(cambios.pop());
                    writer.newLine();
                }
            }
            System.out.println("Cambios guardados en 'cambios.txt'");
        }
    }
}
