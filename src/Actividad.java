import java.util.*;

public final class Actividad {
    private String nombreActividad;
    private Persona encargado;
    private ArrayList<Persona> participantes;
    private static final int capacidadMaxima = 10;

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
        if (encargado != null) {
            System.out.println("Encargado: " + encargado.getName() + " | " + encargado.getRut());
        } else {
            System.out.println("Encargado: Ninguno");
        }
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

    public void addParticipante(Persona persona) throws CapacidadMaximaExcedidaException {
        if (participantes.size() >= capacidadMaxima) {
            throw new CapacidadMaximaExcedidaException(
                "No se puede añadir al participante " + persona.getName() + 
                ". Capacidad máxima de " + capacidadMaxima + " participantes alcanzada en la actividad " + nombreActividad + "."
            );
        }
        participantes.add(persona);
    }
    
    
    public void delPartipante(Persona persona){
        participantes.remove(persona);
    }

    public static int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}