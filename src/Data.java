import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

public class Data {
    
    public static void leerDataActividad(HashMap<String, Actividad> mapa) throws IOException, CapacidadMaximaExcedidaException, FechaInvalidaException {
        String filePathAct = "src/archivoActividades.txt";
        String filePathPart = "src/archivoParticipantes.txt";

        try (BufferedReader lectorAct = new BufferedReader(new FileReader(filePathAct));
             BufferedReader lectorPart = new BufferedReader(new FileReader(filePathPart))) {
            
            String lineaAct;
            String lineaPart;
            
            while ((lineaAct = lectorAct.readLine()) != null && (lineaPart = lectorPart.readLine()) != null) {
                String[] divAct = lineaAct.split(";");
                String[] divPart = lineaPart.split(";");
                
                if (divAct.length < 3) {
                    System.out.println("Error: formato incorrecto en línea de actividad: " + lineaAct);
                    continue;
                }

                String nombreActividad = divAct[0];
                LocalDate fechaInicio = LocalDate.parse(divAct[1]);
                String[] encargado = divAct[2].split("\\|");

                Actividad ActX;
                if (encargado[0].equals("Ninguno")) {
                    ActX = new Actividad(nombreActividad);
                } else {
                    ActX = new Actividad(nombreActividad, new Encargado(encargado[0], encargado[1]));
                }

                try {
                    ActX.setFechaInicio(fechaInicio);
                } catch (FechaInvalidaException e) {
                    System.out.println("Error al establecer la fecha para " + nombreActividad + ": " + e.getMessage());
                    continue;
                }

                for (String part : divPart) {
                    String[] participante = part.split("\\|");
                    if (participante.length == 2) {
                        Participante nuevo = new Participante(participante[0], participante[1]);
                        try {
                            ActX.addParticipante(nuevo);
                        } catch (CapacidadMaximaExcedidaException e) {
                            System.out.println("No se pudo añadir participante a " + nombreActividad + ": " + e.getMessage());
                        }
                    }
                }

                mapa.put(ActX.getActName(), ActX);
            }
        }
    }

    public static void guardarDatos(HashMap<String, Actividad> mapa) throws IOException {
        String filePathAct = "src/archivoActividades.txt";
        String filePathPart = "src/archivoParticipantes.txt";

        try (BufferedWriter escritorAct = new BufferedWriter(new FileWriter(filePathAct));
             BufferedWriter escritorPart = new BufferedWriter(new FileWriter(filePathPart))) {
            for (Actividad actAux : mapa.values()) {
                Encargado encargado = actAux.getEncargado();
                String encargadoInfo = encargado != null ? 
                    encargado.getName() + "|" + encargado.getRut() : 
                    "Ninguno|00000000-0";

                escritorAct.write(actAux.getActName() + ";" + 
                                  actAux.getFechaInicio() + ";" + 
                                  encargadoInfo);
                escritorAct.newLine();

                ArrayList<Participante> participantes = actAux.getParticipantes();
                for (int i = 0; i < participantes.size(); i++) {
                    Persona part = participantes.get(i);
                    escritorPart.write(part.getName() + "|" + part.getRut());
                    if (i < participantes.size() - 1) {
                        escritorPart.write(";");
                    }
                }
                escritorPart.newLine();
            }
        }
    }
}