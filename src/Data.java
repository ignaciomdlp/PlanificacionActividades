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
                String[] divAct = null;
                String[] divPart = null;
                
                if (lineaAct.contains(";")) {
                    divAct = new String[lineaAct.split(";").length];
                    divAct = lineaAct.split(";");}
                else {
                    divAct = new String[1];
                    divAct[0] = lineaAct;
                }
                
                if (lineaPart.contains(";")) {
                    divPart = new String[lineaPart.split(";").length];
                    divPart = lineaPart.split(";");
                }
                else {
                    divPart = new String[1];
                    divPart[0] = lineaPart;
                }

                String[] encargado = divAct[1].split("|");
                Actividad ActX = new Actividad(divAct[0], new Persona(encargado[0], encargado[1]));

                for (int i = 0 ; i < divPart.length ; i++) {
                    String[] participante = divPart[i].split("|");
                    Persona nuevo = new Persona(participante[0], participante[1]);
                    ActX.addParticipante(nuevo);
                }

                mapa.put(ActX.getActName(), ActX);
                }
            }
        }
    }