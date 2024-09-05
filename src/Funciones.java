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

    public static void addActividad(BufferedReader lector, ArrayList<Actividad> lista, HashMap<String, Persona> parExistentes) throws IOException {
        limpiarConsola();
        System.out.println("Dale nombre a la Actividad:");
        String entradaActividad = lector.readLine();
        Actividad ACTX;

        System.out.println("¿Quieres añadir un encargado ahora? (Y/N)");
        String aux = lector.readLine().toLowerCase();

        while (!aux.equals("y") && !aux.equals("n")) {
            System.out.println("Opción Inválida. Intente nuevamente.");
            aux = lector.readLine().toLowerCase();
        }

        if (aux.equals("y")) {
            System.out.println("Ingrese el nombre del encargado:");
            String entradaEncargado = lector.readLine();
            System.out.println("Ingrese el rut del encargado:");
            String entradaRutEncargado = lector.readLine();
            ACTX = new Actividad(entradaActividad, entradaEncargado);
            Persona P0 = new Persona(entradaEncargado, entradaRutEncargado);
            parExistentes.put(entradaRutEncargado, P0);
            System.out.println("Encargado añadido con éxito");
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
                System.out.println("Ingrese el rut del participante:");
                String rutParticipante = lector.readLine();
                Persona P0 = new Persona(participante, rutParticipante);
                parExistentes.put(rutParticipante, P0);
                ACTX.addParticipante(participante);
                System.out.println("Participante añadido con éxito");
                
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
            System.out.println("1. Cambiar nombre\n2. Añadir encargado\n3. Añadir participante\n4. Eliminar personas de la actividad\n5. Volver");
            int opcion = Integer.parseInt(lector.readLine());

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el nuevo nombre de la actividad:");
                    act.setNombreActividad(lector.readLine());
                }
                case 2 -> {
                    if ("N/A".equals(act.getNombreEncargado())){
                        System.out.println("Ingrese el nombre del nuevo encargado:");
                        String nombreEncargado = lector.readLine();
                        System.out.println("Ingrese el rut del nuevo encargado:");
                        String rutEncargado = lector.readLine();
                        Persona P0 = new Persona(nombreEncargado, rutEncargado);
                        parExistentes.put(rutEncargado, P0);
                        act.setNombreEncargado(nombreEncargado);
                        System.out.println("Participante añadido con éxito");
                    } else{
                        System.out.println("Ya hay un encargado, debes eliminarlo de este cargo para agregar a otra persona");
                    }
                }
                case 3 -> {
                    System.out.println("Ingrese el nombre del participante:");
                    String nombreParticipante = lector.readLine();
                    System.out.println("Ingrese el rut del participante:");
                    String rutParticipante = lector.readLine();
                    Persona P0 = new Persona(nombreParticipante, rutParticipante);
                    parExistentes.put(rutParticipante, P0);
                    act.addParticipante(nombreParticipante);
                    System.out.println("Participante añadido con éxito");
                }
                case 4 -> {
                    System.out.println("¿De qué cargo es la persona qué deseas eliminar?: ");
                    System.out.println("1. Encargado\n2. Participante");
                    int op = Integer.parseInt(lector.readLine());
                    
                    //Tal vez sea más ordenado poner todo este switch que sigue en una función aparte
                    switch (op){
                        case 1 -> {
                            System.out.println("Ingrese el rut del encargado para eliminarlo: ");
                            String rutEncargado = lector.readLine();
                            
                            Persona PersE = parExistentes.get(rutEncargado);
                            
                            if (rutEncargado.equals(PersE.getRut())){
                                parExistentes.remove(rutEncargado);
                                act.delEncargado();
                                System.out.println("El encargado ha sido eliminado");
                            } else{
                                System.out.println("No se ha ingresado el rut correctamente...");
                            }
                        }
                        case 2 -> {
                            System.out.println("¿Qué participante deseas eliminar?");
                            String name = lector.readLine();
                            System.out.println("Ingrese el rut de este participante para eliminarlo: ");
                            String rutParticipante = lector.readLine();
                            
                            Persona PersP = parExistentes.get(rutParticipante);
                            
                            if (PersP != null){
                                if (name.equals(PersP.getName())){
                                    parExistentes.remove(rutParticipante);
                                    act.delPartipante(name);
                                    System.out.println("El participante ha sido eliminado");
                                } else{
                                    System.out.println("Se ha ingresado el rut de otra persona");
                                }
                            } else {
                                System.out.println("No se ha ingresado un rut válido...");
                            }
                        }
                        default -> System.out.println("Opción inválida.");
                    }
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