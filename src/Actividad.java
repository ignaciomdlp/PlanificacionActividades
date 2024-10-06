import java.util.*;
import java.time.LocalDate;

public final class Actividad {
    private String nombreActividad;
    private Encargado encargado;
    private ArrayList<Participante> participantes;
    private static final int capacidadMaxima = 10;
    private LocalDate fechaInicio;

    public Actividad(String nombreActividad, Encargado encargado) {
        this.nombreActividad = nombreActividad;
        this.encargado = encargado;
        this.participantes = new ArrayList<Participante>();
    }

    public Actividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
        this.encargado = null;
        participantes = new ArrayList<Participante>();
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
        if (fechaInicio != null) {
            System.out.println("Fecha de inicio: " + fechaInicio);
        } else {
            System.out.println("Fecha de inicio: No establecida");
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

    public void setFechaInicio(LocalDate fechaInicio) throws FechaInvalidaException {
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new FechaInvalidaException("La fecha de inicio " + fechaInicio + " ya ha pasado. Seleccione una fecha futura.");
        }
        this.fechaInicio = fechaInicio; // Asignar la fecha si es válida
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    public Encargado getEncargado() {
        return encargado;
    }
    
    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setEncargado(Encargado encargado) {
        if (this.encargado != null) {System.out.println("No se pudo añadir el encargado porque ya hay uno.");}
        else {this.encargado = encargado;}
    }
    
    public void delEncargado(Encargado persona){
        this.encargado = null;
    }

    public void addParticipante(Participante participante) throws CapacidadMaximaExcedidaException {
        if (participantes.size() >= capacidadMaxima) {
            throw new CapacidadMaximaExcedidaException(
                "No se puede añadir al participante " + participante.getName() + 
                ". Capacidad máxima de " + capacidadMaxima + " participantes alcanzada en la actividad " + nombreActividad + "."
            );
        }
        participantes.add(participante);
    }
    
    
    public void delParticipante(Participante participante){
        participantes.remove(participante);
    }

    public static int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}
