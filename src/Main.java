
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        HashMap<String, Persona> listaPersonas = new HashMap<>();
        
        //Lectura de Actividades y Personas.
        
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        int opcionMPrincipal = 0;
        while (opcionMPrincipal != 3) {
            System.out.println("---|Gestor de Actividades de Universidad|---");
            System.out.println("  1|Mostrar Actividades\n  2|Gestionar Actividades\n  3|Salir");
            System.out.println("Ingrese una opción:");

            opcionMPrincipal = Integer.parseInt(scanner.readLine());
            switch (opcionMPrincipal) {
                case 1 -> Funciones.listarActividades(listaActividades);
                case 2 -> {
                    int opcionMGestion = 0;
                    while (opcionMGestion != 4) {
                        System.out.println("---|           Menú de Gestión          |---");
                        System.out.println("  1|Añadir una Actividad\n  2|Modificar Actividad\n  3|Eliminar Actividad\n  4|Salir");
                        System.out.println("Ingrese una opción:");

                        opcionMGestion = Integer.parseInt(scanner.readLine());
                        switch (opcionMGestion) {
                            case 1 -> Funciones.addActividad(scanner, listaActividades);
                            case 2 -> Funciones.modActividad(scanner, listaActividades, listaPersonas);
                            case 3 -> Funciones.delActividad(scanner, listaActividades);
                            case 4 -> System.out.println("Saliendo del menú de gestión...");
                            default -> System.out.println("Opción inválida.");
                        }
                    }
                }
                case 3 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
