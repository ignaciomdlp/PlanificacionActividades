import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Data {
    public static void leerDataActividad(HashMap<String, Actividad> mapa) {
        InputStream inputStream = Data.class.getResourceAsStream("/archivoActividades.txt");
        
        if (inputStream != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String linea;
                String nombreAct = null;
                String nombreEncargado = null;
                
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");
                    
                    if (nombreAct == null) {
                        // Línea de actividad y encargado
                        nombreAct = partes[0].trim();
                        nombreEncargado = partes[1].trim();
                        mapa.put(nombreAct, new Actividad(nombreEncargado));
                    } else {
                        // Línea de participantes
                        Actividad actividad = mapa.get(nombreAct);
                        if (actividad != null) {
                            for (String participante : partes) {
                                String[] dataPart = participante.split("|");
                                Persona nuevoPart = new Persona(dataPart[0].trim(), dataPart[1].trim());
                                actividad.addParticipante(nuevoPart);
                            }
                        }
                        // Preparar para la próxima actividad
                        nombreAct = null;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.err.println("Archivo no encontrado.");
        }
    }

}