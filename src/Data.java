import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Data {
    public static void leerDataActividad() {
        InputStream inputStream = Data.class.getResourceAsStream("/archivoActividades.txt");
        
        if (inputStream != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String linea;
                int i = 0;

                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (i % 2 == 0) {
                        // Línea de actividad y encargado
                        System.out.println("Actividad: " + partes[0].trim());
                        System.out.println("Encargado: " + partes[1].trim());
                    } else {
                        // Línea de participantes
                        System.out.print("Participantes: ");
                        for (int j = 0; j < partes.length; j++) {
                            System.out.print(partes[j].trim());
                            if (j < partes.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();
                    }
                    i++;
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.err.println("Archivo no encontrado.");
        }
    }
}
