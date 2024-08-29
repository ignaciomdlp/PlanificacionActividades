import java.util.*;

public class Actividad {
    private String nombreActividad;
    private String nombreEncargado;
    private ArrayList<String> listaParticipantes;

    public Actividad(String entradaNombre, String entradaEncargado) {
        nombreActividad = entradaNombre;
        nombreEncargado = entradaEncargado;
        listaParticipantes = new ArrayList<String>();
    }

    public Actividad(String entradaNombre) {
        nombreActividad = entradaNombre;
        nombreEncargado = "N/A";
        listaParticipantes = new ArrayList<String>();
    }

    public void mostrarInfo() {
        System.out.println("Actividad: " + nombreActividad);
        System.out.println("Encargado: " + nombreEncargado);
        if (!listaParticipantes.isEmpty()) {
            System.out.println("Participantes:");
            for (String participante : listaParticipantes) {
                System.out.println(participante);
            }
        } else {
            System.out.println("No hay participantes registrados.");
        }
    }

    public String getActName() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public void addParticipante(String participante) {
        listaParticipantes.add(participante);
    }
}