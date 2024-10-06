import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class Funciones {

    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void listarActividades(HashMap<String, Actividad> mapaActividades) {
        if (mapaActividades.isEmpty()) {
            System.out.println("No hay actividades registradas.");
        } else {
            for (Actividad actividad : mapaActividades.values()) {
                actividad.mostrarInfo(); // Utiliza el método mostrarInfo() para imprimir detalles
            }
        }
    }

    public static void addActividad(BufferedReader reader, HashMap<String, Actividad> parExistentes) throws IOException {
        limpiarConsola();
        System.out.println("Ingrese un nombre para la Actividad:");
        String entradaActividad = reader.readLine();
        
        // Inicializacion ACTX con una actividad sin encargado
        Actividad ACTX = new Actividad(entradaActividad); 
    
        System.out.println("¿Quieres añadir un encargado ahora? (Y/N)");
        String aux = reader.readLine().toLowerCase();
    
        while (!aux.equals("y") && !aux.equals("n")) {
            System.out.println("Opción Inválida. Intente nuevamente.");
            aux = reader.readLine().toLowerCase();
        }
    
        if (aux.equals("y")) {
            System.out.println("Ingrese el nombre del encargado:");
            String entradaEncargado = reader.readLine();
    
            String entradaRutEncargado;
            boolean rutValido = false;
    
            do {
                System.out.println("Ingrese el rut del encargado:");
                entradaRutEncargado = reader.readLine();
    
                try {
                    Persona P0 = new Persona(entradaEncargado, entradaRutEncargado);
                    rutValido = true;
                    ACTX = new Actividad(entradaActividad, P0); // Reasignacion ACTX con encargado
                    System.out.println("Encargado añadido con éxito");
                } catch (IllegalArgumentException e) {
                    System.out.println("RUT inválido. Debe ser en el formato 00000000-0. Intente nuevamente.");
                }
            } while (!rutValido);
        }
    
        System.out.println("¿Quieres añadir participantes ahora? (Y/N)");
        aux = reader.readLine().toLowerCase();
    
        while (!aux.equals("y") && !aux.equals("n")) {
            System.out.println("Opción Inválida. Intente nuevamente.");
            aux = reader.readLine().toLowerCase();
        }
    
        if (aux.equals("y")) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("Ingrese el nombre del participante:");
                String participante = reader.readLine();
    
                System.out.println("Ingrese el rut del participante:");
                String rutParticipante = reader.readLine();
    
                try {
                    Persona P0 = new Persona(participante, rutParticipante);
                    ACTX.addParticipante(P0);
                    System.out.println("Participante añadido con éxito");
                } catch (IllegalArgumentException e) {
                    System.out.println("RUT inválido. Debe ser en el formato 00000000-0. Intente nuevamente.");
                }
    
                System.out.println("¿Deseas agregar otro participante? (Y/N)");
                aux = reader.readLine().toLowerCase();
                continuar = aux.equals("y");
            }
        }
    
        parExistentes.put(entradaActividad, ACTX);
        System.out.println("Actividad añadida correctamente.");
        ACTX.mostrarInfo();
    }
    

    public static void delActividad(BufferedReader reader, HashMap<String, Actividad> parExistentes) throws IOException {
        listarActividades(parExistentes);
        if (parExistentes.isEmpty()) return;

        System.out.println("Ingrese el nombre de la actividad que desea eliminar:");
        String nombre = reader.readLine();

        if (!parExistentes.containsKey(nombre)) {
            System.out.println("Nombre inválido.");
        } else {
            parExistentes.remove(nombre);
            System.out.println("Actividad eliminada correctamente.");
        }
    }

    public static void modActividad(BufferedReader reader, HashMap<String, Actividad> parExistentes) throws IOException 
    {
        listarActividades(parExistentes);
        if (parExistentes.isEmpty()) return;
    
        System.out.println("Ingrese el nombre de la Actividad que desea modificar:");
        String entradaActividad = reader.readLine();
    
        if (parExistentes.containsKey(entradaActividad)) {
            Actividad act = parExistentes.get(entradaActividad);
            System.out.println("Actividad seleccionada: " + act.getActName());
            System.out.println("1. Cambiar nombre\n2. Añadir encargado\n3. Añadir participante\n4. Eliminar personas de la actividad\n5. Volver");
            int opcion = Integer.parseInt(reader.readLine());
    
            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el nuevo nombre de la actividad:");
                    act.setNombreActividad(act.getActName(), reader.readLine(), parExistentes);
                }
                case 2 -> {
                    if ("N/A".equals(act.getEncargado().getName())) {
                        System.out.println("Ingrese el nombre del nuevo encargado:");
                        String nombreEncargado = reader.readLine();
                        System.out.println("Ingrese el rut del nuevo encargado:");
                        String rutEncargado = reader.readLine();
    
                        try {
                            Persona P0 = new Persona(nombreEncargado, rutEncargado);
                            act.setEncargado(P0);
                            System.out.println("Encargado añadido con éxito");
                        } catch (IllegalArgumentException e) {
                            System.out.println("RUT inválido. Debe ser en el formato 00000000-0. Intente nuevamente.");
                        }
                    } else {
                        System.out.println("Ya hay un encargado, debes eliminarlo de este cargo para agregar a otra persona.");
                    }
                }
                case 3 -> {
                    System.out.println("Ingrese el nombre del participante:");
                    String nombreParticipante = reader.readLine();
                    System.out.println("Ingrese el rut del participante:");
                    String rutParticipante = reader.readLine();
    
                    try {
                        Persona P0 = new Persona(nombreParticipante, rutParticipante);
                        act.addParticipante(P0);
                        System.out.println("Participante añadido con éxito");
                    } catch (IllegalArgumentException e) {
                        System.out.println("RUT inválido. Debe ser en el formato 00000000-0. Intente nuevamente.");
                    }
                }
                case 4 -> {
                    System.out.println("¿De qué cargo es la persona que deseas eliminar?: ");
                    System.out.println("1. Encargado\n2. Participante");
                    int op = Integer.parseInt(reader.readLine());
    
                    switch (op) {
                        case 1 -> {
                            System.out.println("Ingrese el rut del encargado para eliminarlo: ");
                            String rutEncargado = reader.readLine();
    
                            Persona PersE = act.getEncargado();
    
                            if (rutEncargado.equals(PersE.getRut())) {
                                act.delEncargado(PersE);
                                System.out.println("El encargado ha sido eliminado");
                            } else {
                                System.out.println("No se ha ingresado el rut correctamente...");
                            }
                        }
                        case 2 -> {
                            System.out.println("¿Qué participante deseas eliminar?");
                            String name = reader.readLine();
                            System.out.println("Ingrese el rut de este participante para eliminarlo: ");
                            String rutParticipante = reader.readLine();
    
                            ArrayList<Persona> participantes = act.getParticipantes();
                            boolean eliminated = false;
    
                            for (Persona p : participantes) {
                                if (name.equals(p.getName()) && rutParticipante.equals(p.getRut())) {
                                    act.delPartipante(p);
                                    eliminated = true;
                                }
                            }
                            if (eliminated) {
                                System.out.println("El participante ha sido eliminado");
                            } else {
                                System.out.println("No se ha ingresado un rut válido...");
                            }
                        }
                        default -> System.out.println("Opción inválida.");
                    }
                }
                default -> System.out.println("Opción inválida.");
            }
            act.mostrarInfo();
        } else {
            System.out.println("Nombre inválido.");
        }
    }}
    
    