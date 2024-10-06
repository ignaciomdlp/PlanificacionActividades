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
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
                Ventana v = new Ventana(mapaActividades, reader);
                v.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer los datos: " + e.getMessage());
            } catch (CapacidadMaximaExcedidaException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
