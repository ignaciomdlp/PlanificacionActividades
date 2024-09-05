import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Funciones {

    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

public static void listarActividades(ArrayList<Actividad> lista) {
    if (lista.isEmpty()) {
        System.out.println("No hay actividades registradas.");
    } else {
        for (int i = 0; i < lista.size(); i++) {
            Actividad actividad = lista.get(i);
            System.out.println((i + 1) + ". " + actividad.getActName());
            actividad.mostrarInfo(); // Utiliza el método mostrarInfo() para imprimir detalles
        }
    }
}

    public static void addActividad(BufferedReader lector, ArrayList<Actividad> lista) throws IOException {
        limpiarConsola();
        System.out.println("Dale nombre a la Actividad:");
        String entradaActividad = lector.readLine();
        Actividad ACTX = null;

        System.out.println("¿Quieres añadir un encargado ahora? (Y/N)");
        String aux = lector.readLine().toLowerCase();

        while (!aux.equals("y") && !aux.equals("n")) {
            System.out.println("Opción Inválida. Intente nuevamente.");
            aux = lector.readLine().toLowerCase();
        }

        if (aux.equals("y")) {
            System.out.println("Ingrese el nombre del encargado:");
            String entradaEncargado = lector.readLine();
            ACTX = new Actividad(entradaActividad, entradaEncargado);
        } else {
            ACTX = new Actividad(entradaActividad);
        }

        System.out.println("¿Quieres añadir participantes ahora? (Y/N)");
        aux = lector.readLine().toLowerCase();

        while (!aux.equals("y") && !aux.equals("n")) {
            System.out.println("Opción Inválida. Intente nuevamente.");
            aux = lector.readLine().toLowerCase();
        }

        if (aux.equals("y")) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("Ingrese el nombre del participante:");
                String participante = lector.readLine();
                ACTX.addParticipante(participante);

                System.out.println("¿Deseas agregar otro participante? (Y/N)");
                aux = lector.readLine().toLowerCase();
                continuar = aux.equals("y");
            }
        }

        lista.add(ACTX);
        System.out.println("Actividad añadida correctamente.");
        ACTX.mostrarInfo();
    }

    public static void delActividad(BufferedReader lector, ArrayList<Actividad> lista) throws IOException {
        listarActividades(lista);
        if (lista.isEmpty()) return;

        System.out.println("Seleccione el número de la actividad que desea eliminar:");
        int index = Integer.parseInt(lector.readLine()) - 1;

        if (index >= 0 && index < lista.size()) {
            lista.remove(index);
            System.out.println("Actividad eliminada correctamente.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    public static void modActividad(BufferedReader lector, ArrayList<Actividad> listaAct, HashMap<String, Persona> parExistentes) throws IOException {
        listarActividades(listaAct);
        if (listaAct.isEmpty()) return;

        System.out.println("Seleccione el número de la actividad que desea modificar:");
        int index = Integer.parseInt(lector.readLine()) - 1;

        if (index >= 0 && index < listaAct.size()) {
            Actividad act = listaAct.get(index);
            System.out.println("Actividad seleccionada: " + act.getActName());
            System.out.println("1. Cambiar nombre\n2. Cambiar encargado\n3. Añadir participante\n4. Volver");
            int opcion = Integer.parseInt(lector.readLine());

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el nuevo nombre de la actividad:");
                    act.setNombreActividad(lector.readLine());
                }
                case 2 -> {
                    System.out.println("Ingrese el nuevo nombre del encargado:");
                    act.setNombreEncargado(lector.readLine());
                }
                case 3 -> {
                    System.out.println("Ingrese el nombre del participante:");
                    String nombreParticipante = lector.readLine();
                    String rutParticipante = lector.readLine();
                    Persona P0 = new Persona(nombreParticipante, rutParticipante);
                    parExistentes.put(rutParticipante, P0);
                    act.addParticipante(rutParticipante);
                    System.out.println("Participante añadido con éxito");
                }
                default -> System.out.println("Opción inválida.");
            }
            // Muestra la información actualizada
            act.mostrarInfo();
        } else {
            System.out.println("Número inválido.");
        }
    }
}
