import java.util.*;

public class Actividad {
    private String nombreActividad;
    private String nombreEncargado;
    private ArrayList<String> clavesParticipantes;

    public Actividad(String entradaNombre, String entradaEncargado) {
        nombreActividad = entradaNombre;
        nombreEncargado = entradaEncargado;
        clavesParticipantes = new ArrayList<>();
    }

    public Actividad(String entradaNombre) {
        nombreActividad = entradaNombre;
        nombreEncargado = "N/A";
        clavesParticipantes = new ArrayList<>();
    }

    public void mostrarInfo() {
        System.out.println("Actividad: " + nombreActividad);
        System.out.println("Encargado: " + nombreEncargado);
        if (!clavesParticipantes.isEmpty()) {
            System.out.println("Participantes:");
            for (String participante : clavesParticipantes) {
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
    
    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }
    
    public void delEncargado(){
        this.nombreEncargado = "N/A";
    }

    public void addParticipante(String clave) {
        clavesParticipantes.add(clave);
    }
    
    public void delPartipante(String clave){
        clavesParticipantes.remove(clave);
    }
}