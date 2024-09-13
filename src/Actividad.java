import java.util.*;

public final class Actividad {
    private String nombreActividad;
    private Persona encargado;
    private ArrayList<Persona> participantes;

    public Actividad(String nombreActividad, Persona encargado) {
        this.nombreActividad = nombreActividad;
        this.encargado = encargado;
        this.participantes = new ArrayList<Persona>();
    }

    public Actividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
        this.encargado = null;
        participantes = new ArrayList<Persona>();
    }

    public void mostrarInfo() {
        System.out.println("\nActividad: " + nombreActividad);
        System.out.println("Encargado: " + encargado.getName() + " | " + encargado.getRut());
        System.out.println("Participantes:");
        for (Persona p : participantes) {
            System.out.println("- " + p.getName() + " | " + p.getRut());
        }
    }

    public String getActName() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreAntiguo, String nombreActividad, HashMap<String, Actividad> mapa) {
        this.nombreActividad = nombreActividad;
        Actividad ACT = mapa.get(nombreAntiguo);
        mapa.remove(nombreAntiguo);
        mapa.put(nombreActividad, ACT);
    }
    
    public Persona getEncargado() {
        return encargado;
    }
    
    public ArrayList<Persona> getParticipantes() {
        return participantes;
    }

    public void setEncargado(Persona encargado) {
        if (this.encargado != null) {System.out.println("No se pudo añadir el encargado porque ya hay uno.");}
        else {this.encargado = encargado;}
    }
    
    public void delEncargado(Persona persona){
        this.encargado = null;
    }

    public void addParticipante(Persona persona) {
        participantes.add(persona);
    }
    
    public void delPartipante(Persona persona){
        participantes.remove(persona);
    }
}