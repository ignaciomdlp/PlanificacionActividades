import java.util.*;

public final class Actividad {
    private String nombreActividad;
    private ArrayList<Persona> participantes;

    public Actividad(String nombreActividad, Persona encargado) {
        this.nombreActividad = nombreActividad;
        setEncargado(encargado);
        this.participantes = new ArrayList<>();
    }

    public Actividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
        this.participantes = new ArrayList<>();
    }

    public void mostrarInfo() {
        System.out.println("Actividad: " + nombreActividad);
        System.out.println("Encargado: " + (getEncargado() != null ? getEncargado().getName() : "N/A"));
        System.out.println("Participantes:");
        for (Persona p : participantes) {
            if (!"Encargado".equals(p.getCargo())) {
                System.out.println("- " + p.getName() + " (" + p.getCargo() + ")");
            }
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
        for (Persona p : participantes) {
            if ("Encargado".equals(p.getCargo())) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList getParticipantes() {
        return participantes;
    }

    public void setEncargado(Persona persona) {
        persona.setCargo("Encargado");
        if (!participantes.contains(persona)){
            participantes.add(persona);
        }
    }
    
    public void delEncargado(Persona persona){
        persona.setCargo("N/A");
        participantes.remove(persona);
    }

    public void addParticipante(Persona persona) {
        participantes.add(persona);
    }
    
    public void delPartipante(Persona persona){
        participantes.remove(persona);
    }
}