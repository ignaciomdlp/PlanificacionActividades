import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Data {
    public static void leerDataActividad(HashMap<String, Actividad> mapa) throws IOException {
        InputStream streamAct = Data.class.getResourceAsStream("/archivoActividades.txt");
        InputStream streamPart = Data.class.getResourceAsStream("/archivoParticipantes.txt");
        
        if (streamAct != null && streamPart != null) {
            BufferedReader lectorAct = new BufferedReader(new InputStreamReader(streamAct));
            BufferedReader lectorPart = new BufferedReader(new InputStreamReader(streamPart));
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

                String[] encargado = divAct[1].split("|");
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
}