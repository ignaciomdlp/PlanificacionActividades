import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class Data {
    
    public static void leerDataActividad(HashMap<String, Actividad> mapa) throws IOException {
        // Use absolute paths for debugging purposes
        String filePathAct = "src/archivoActividades.txt";
        String filePathPart = "src/archivoParticipantes.txt";

        try (BufferedReader lectorAct = new BufferedReader(new FileReader(filePathAct));
        BufferedReader lectorPart = new BufferedReader(new FileReader(filePathPart))) {
            
            String lineaAct;
            String lineaPart;
            
            while ((lineaAct = lectorAct.readLine()) != null && (lineaPart = lectorPart.readLine()) != null) {
                String[] divAct;
                String[] divPart;
                
                if (lineaAct.contains(";")) {
                    divAct = lineaAct.split(";");
                } else {
                    divAct = new String[]{lineaAct};
                }
                
                if (lineaPart.contains(";")) {
                    divPart = lineaPart.split(";");
                } else {
                    divPart = new String[]{lineaPart};
                }

                String[] encargado = divAct[1].split("\\|");
                Actividad ActX = new Actividad(divAct[0], new Persona(encargado[0], encargado[1]));

                for (String part : divPart) {
                    String[] participante = part.split("\\|");
                    Persona nuevo = new Persona(participante[0], participante[1]);
                    ActX.addParticipante(nuevo);
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
            Actividad actAux = null;
            for (String key : mapa.keySet()) {
                actAux = mapa.get(key);

                Persona encargado = actAux.getEncargado();
                escritorAct.write(actAux.getActName() + ";" + encargado.getName() + "|" + encargado.getRut());
                escritorAct.newLine();

                ArrayList<Persona> participantes = actAux.getParticipantes();
                for (int i = 0 ; i < participantes.size() ; i++) {
                    Persona part = participantes.get(i);
                    escritorPart.write(part.getName() + "|" + part.getRut());
                    if (i < participantes.size() - 1) {escritorPart.write(";");}
                }
                escritorPart.newLine();
            }
        }
    }
}