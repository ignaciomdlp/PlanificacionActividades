import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CasosBase {

    public static void casoBase1(ArrayList<Actividad> listaActividades) {
        System.out.println("Creando Actividad: 'Curso de Introducción a la Programación'");
        Actividad actividad = new Actividad("Curso de Introducción a la Programación");
        listaActividades.add(actividad);
        System.out.println("Actividad creada sin encargado ni participantes.");
        actividad.mostrarInfo();
    }

    public static void casoBase2(ArrayList<Actividad> listaActividades) {
        System.out.println("Creando Actividad: 'Proyecto Final de Ingeniería'");
        Actividad actividad = new Actividad("Proyecto Final de Ingeniería", "Dr. Juan Pérez");
        listaActividades.add(actividad);
        System.out.println("Actividad creada con encargado pero sin participantes.");
        actividad.mostrarInfo();
    }

    public static void casoBase3(ArrayList<Actividad> listaActividades) {
        System.out.println("Creando Actividad: 'Seminario de Investigación'");
        Actividad actividad = new Actividad("Seminario de Investigación", "Dra. María Gómez");
        actividad.addParticipante("Pedro Martínez");
        actividad.addParticipante("Lucía Hernández");
        actividad.addParticipante("Carlos Pérez");
        listaActividades.add(actividad);
        System.out.println("Actividad creada con encargado y participantes.");
        actividad.mostrarInfo();
    }

    public static void casoBase4(ArrayList<Actividad> listaActividades) {
        if (!listaActividades.isEmpty()) {
            System.out.println("Modificando la primera actividad en la lista...");
            Actividad actividad = listaActividades.get(0);
            actividad.setNombreActividad("Curso Avanzado de Programación");
            actividad.addParticipante("Ana García");
            System.out.println("Actividad modificada.");
            actividad.mostrarInfo();
        } else {
            System.out.println("No hay actividades para modificar.");
        }
    }

    public static void casoBase5(ArrayList<Actividad> listaActividades) {
        if (!listaActividades.isEmpty()) {
            System.out.println("Eliminando la primera actividad en la lista...");
            listaActividades.remove(0);
            System.out.println("Actividad eliminada.");
        } else {
            System.out.println("No hay actividades para eliminar.");
        }
    }
}
