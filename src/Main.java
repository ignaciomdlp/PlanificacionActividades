import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, Actividad> mapaActividades = new HashMap<>();
        Data.leerDataActividad(mapaActividades);
        // Lectura de Actividades y Personas.
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int opcionMPrincipal = 0;
        while (opcionMPrincipal != 3) {
            System.out.println("---|Gestor de Actividades de Universidad|---");
            System.out.println("  1|Mostrar Actividades\n  2|Gestionar Actividades\n  3|Salir");
            System.out.println("Ingrese una opción:");

            try {
                opcionMPrincipal = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                continue;
            }

            switch (opcionMPrincipal) {
                case 1 -> Funciones.listarActividades(mapaActividades);
                case 2 -> {
                    int opcionMGestion = 0;
                    while (opcionMGestion != 4) {
                        System.out.println("---|           Menú de Gestión          |---");
                        System.out.println("  1|Añadir una Actividad\n  2|Modificar Actividad\n  3|Eliminar Actividad\n  4|Salir");
                        System.out.println("Ingrese una opción:");

                        try {
                            opcionMGestion = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opción inválida. Por favor, ingrese un número.");
                            continue;
                        }

                        switch (opcionMGestion) {
                            case 1 -> Funciones.addActividad(reader, mapaActividades);
                            case 2 -> Funciones.modActividad(reader, mapaActividades);
                            case 3 -> Funciones.delActividad(reader, mapaActividades);
                            case 4 -> System.out.println("Saliendo del menú de gestión...");
                            default -> System.out.println("Opción inválida.");
                        }
                    }
                }
                case 3 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        }
        Data.guardarDatos(mapaActividades);
        reader.close();
    }
}