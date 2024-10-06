
import javax.swing.*;
import java.awt.*; 
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Ventana extends JFrame {
    private HashMap<String, Actividad> mapaActividades;
    private BufferedReader reader;
    private JPanel panelActual;

    public Ventana(HashMap<String, Actividad> mapaActividades, BufferedReader reader) {
        this.mapaActividades = mapaActividades;
        this.reader = reader;
        
        setTitle("---|Gestor de Actividades de Universidad|---");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mostrarPanelPrincipal();
    }

    private void mostrarPanelPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("---|Gestor de Actividades de Universidad|---");
        titulo.setFont(new Font("times new roman", Font.BOLD, 25));
        JButton btnMostrar = new JButton("Mostrar Actividades");
        btnMostrar.setFont(new Font("times new roman", Font.PLAIN, 15));
        JButton btnGestionar = new JButton("Gestionar Actividades");
        btnGestionar.setFont(new Font("times new roman", Font.PLAIN, 15));
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        btnMostrar.addActionListener(e -> mostrarActividades());
        btnGestionar.addActionListener(e -> mostrarMenuGestion());
        btnSalir.addActionListener(e -> {
            try {
                Data.guardarDatos(mapaActividades);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar datos: " + ex.getMessage());
            }
            System.exit(0);
        });
        
        panel.add(titulo);
        panel.add(btnMostrar);
        panel.add(btnGestionar);
        panel.add(btnSalir);
        
        cambiarPanel(panel);
    }

    private void mostrarActividades() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JTextArea areaTexto = new JTextArea(20, 40);
        areaTexto.setFont(new Font("times new roman", Font.BOLD, 15));
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        
        if (mapaActividades.isEmpty()) {
            areaTexto.setText("No hay actividades registradas.");
        } else {
            for (Actividad actividad : mapaActividades.values()) {
                areaTexto.append(actividadToString(actividad));
                areaTexto.append("\n\n");
            }
        }
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarPanelPrincipal());
        
        panel.add(scrollPane);
        panel.add(btnVolver);
        
        cambiarPanel(panel);
    }

    private void mostrarMenuGestion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("---|Menú de Gestión|---");
        titulo.setFont(new Font("times new roman", Font.BOLD, 25));
        
        JButton btnAñadir = new JButton("Añadir una Actividad");
        btnAñadir.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnModificar = new JButton("Modificar Actividad");
        btnModificar.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnEliminar = new JButton("Eliminar Actividad");
        btnEliminar.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        
        btnAñadir.addActionListener(e -> añadirActividad());
        btnModificar.addActionListener(e -> modificarActividad());
        btnEliminar.addActionListener(e -> eliminarActividad());
        btnVolver.addActionListener(e -> mostrarPanelPrincipal());
        
        panel.add(titulo);
        panel.add(btnAñadir);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnVolver);
        
        cambiarPanel(panel);
    }

    private void añadirActividad() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JTextField nombreField = new JTextField(20);
        JTextField encargadoNombreField = new JTextField(20);
        JTextField encargadoRutField = new JTextField(20);
        JTextField fechaField = new JTextField(20);
        
        JButton btnAñadir = new JButton("Añadir Actividad");
        btnAñadir.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        btnAñadir.addActionListener(e -> {
            String nombre = nombreField.getText();
            String encargadoNombre = encargadoNombreField.getText();
            String encargadoRut = encargadoRutField.getText();
            String fechaStr = fechaField.getText();
            
            if (!nombre.isEmpty() && !encargadoNombre.isEmpty() && !encargadoRut.isEmpty() && !fechaStr.isEmpty()) {
                try {
                    LocalDate fecha = LocalDate.parse(fechaStr);
                    Encargado encargado = new Encargado(encargadoNombre, encargadoRut);
                    Actividad nuevaActividad = new Actividad(nombre, encargado);
                    nuevaActividad.setFechaInicio(fecha);
                    mapaActividades.put(nombre, nuevaActividad);
                    JOptionPane.showMessageDialog(this, "Actividad añadida con éxito");
                    mostrarMenuGestion();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use el formato YYYY-MM-DD.");
                } catch (FechaInvalidaException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
            }
        });
        
        btnVolver.addActionListener(e -> mostrarMenuGestion());
        
        panel.add(new JLabel("Nombre de la Actividad:"));
        panel.add(nombreField);
        panel.add(new JLabel("Nombre del Encargado:"));
        panel.add(encargadoNombreField);
        panel.add(new JLabel("RUT del Encargado:"));
        panel.add(encargadoRutField);
        panel.add(new JLabel("Fecha de Inicio (YYYY-MM-DD):"));
        panel.add(fechaField);
        panel.add(btnAñadir);
        panel.add(btnVolver);
        
        cambiarPanel(panel);
    }

    private void modificarActividad() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JComboBox<String> actividadesCombo = new JComboBox<>(mapaActividades.keySet().toArray(new String[0]));
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        btnModificar.addActionListener(e -> {
            String nombreActividad = (String) actividadesCombo.getSelectedItem();
            if (nombreActividad != null) {
                mostrarOpcionesModificacion(nombreActividad);
            }
        });
        
        btnVolver.addActionListener(e -> mostrarMenuGestion());
        
        panel.add(new JLabel("Seleccione la actividad a modificar:"));
        panel.add(actividadesCombo);
        panel.add(btnModificar);
        panel.add(btnVolver);
        
        cambiarPanel(panel);
    }

    private void mostrarOpcionesModificacion(String nombreActividad) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JButton btnCambiarNombre = new JButton("Cambiar nombre");
        btnCambiarNombre.setFont(new Font("times new roman", Font.PLAIN, 15));

        JButton btnCambiarFecha = new JButton("Cambiar fecha");
        btnCambiarFecha.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnAñadirEncargado = new JButton("Añadir encargado");
        btnAñadirEncargado.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnAñadirParticipante = new JButton("Añadir participante");
        btnAñadirParticipante.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnEliminarPersona = new JButton("Eliminar personas de la actividad");
        btnEliminarPersona.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        btnCambiarNombre.addActionListener(e -> cambiarNombreActividad(nombreActividad));
        btnAñadirEncargado.addActionListener(e -> añadirEncargadoActividad(nombreActividad));
        btnAñadirParticipante.addActionListener(e -> {
            añadirParticipanteActividad(nombreActividad);
        });
        btnEliminarPersona.addActionListener(e -> eliminarPersonaActividad(nombreActividad));
        btnVolver.addActionListener(e -> modificarActividad());

        btnCambiarFecha.addActionListener(e -> cambiarFechaActividad(nombreActividad));
        
        panel.add(new JLabel("Opciones de modificación para: " + nombreActividad));
        panel.add(btnCambiarNombre);
        panel.add(btnCambiarFecha);
        panel.add(btnAñadirEncargado);
        panel.add(btnAñadirParticipante);
        panel.add(btnEliminarPersona);
        panel.add(btnVolver);
        
        cambiarPanel(panel);
    }

    private void cambiarNombreActividad(String nombreActividad) {
        String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre de la actividad:");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            Actividad actividad = mapaActividades.get(nombreActividad);
            actividad.setNombreActividad(nombreActividad, nuevoNombre, mapaActividades);
            JOptionPane.showMessageDialog(this, "Nombre de la actividad cambiado con éxito");
            mostrarOpcionesModificacion(nuevoNombre);
        }
    }

    private void añadirEncargadoActividad(String nombreActividad) {
        Actividad actividad = mapaActividades.get(nombreActividad);
        if (actividad.getEncargado() == null) {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del encargado:");
            String rut = JOptionPane.showInputDialog(this, "Ingrese el RUT del encargado:");
            if (nombre != null && !nombre.isEmpty() && rut != null && !rut.isEmpty()) {
                Encargado encargado = new Encargado(nombre, rut);
                actividad.setEncargado(encargado);
                JOptionPane.showMessageDialog(this, "Encargado añadido con éxito");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Esta actividad ya tiene un encargado");
        }
    }
    
    private void cambiarFechaActividad(String nombreActividad) {
        String nuevaFechaStr = JOptionPane.showInputDialog(this, "Ingrese la nueva fecha de la actividad (YYYY-MM-DD):");
        if (nuevaFechaStr != null && !nuevaFechaStr.isEmpty()) {
            try {
                LocalDate nuevaFecha = LocalDate.parse(nuevaFechaStr);
                Actividad actividad = mapaActividades.get(nombreActividad);
                actividad.setFechaInicio(nuevaFecha);
                JOptionPane.showMessageDialog(this, "Fecha de la actividad cambiada con éxito");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use el formato YYYY-MM-DD.");
            } catch (FechaInvalidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private void añadirParticipanteActividad(String nombreActividad) {
        Actividad actividad = mapaActividades.get(nombreActividad);
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del participante:");
        String rut = JOptionPane.showInputDialog(this, "Ingrese el RUT del participante:");
        if (nombre != null && !nombre.isEmpty() && rut != null && !rut.isEmpty()) {
            try {
                Participante participante = new Participante(nombre, rut);
                actividad.addParticipante(participante);
                JOptionPane.showMessageDialog(this, "Participante añadido con éxito");
            } catch (CapacidadMaximaExcedidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "RUT inválido. Debe ser en el formato 00000000-0.");
            }
        }
    }

    private void eliminarPersonaActividad(String nombreActividad) {
        Actividad actividad = mapaActividades.get(nombreActividad);
        String[] opciones = {"Encargado", "Participante"};
        int seleccion = JOptionPane.showOptionDialog(this, "¿Qué tipo de persona desea eliminar?", "Eliminar Persona",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        if (seleccion == 0) { // Eliminar Encargado
            if (actividad.getEncargado() != null) {
                actividad.delEncargado(actividad.getEncargado());
                JOptionPane.showMessageDialog(this, "Encargado eliminado con éxito");
            } else {
                JOptionPane.showMessageDialog(this, "Esta actividad no tiene encargado");
            }
        } else if (seleccion == 1) { // Eliminar Participante
            ArrayList<Participante> participantes = actividad.getParticipantes();
            if (!participantes.isEmpty()) {
                String[] nombresParticipantes = participantes.stream().map(Persona::getName).toArray(String[]::new);
                String seleccionado = (String) JOptionPane.showInputDialog(this, "Seleccione el participante a eliminar:",
                        "Eliminar Participante", JOptionPane.QUESTION_MESSAGE, null, nombresParticipantes, nombresParticipantes[0]);
                if (seleccionado != null) {
                    Participante participanteAEliminar = participantes.stream()
                            .filter(p -> p.getName().equals(seleccionado))
                            .findFirst()
                            .orElse(null);
                    if (participanteAEliminar != null) {
                        actividad.delParticipante(participanteAEliminar);
                        JOptionPane.showMessageDialog(this, "Participante eliminado con éxito");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Esta actividad no tiene participantes");
            }
        }
    }

    private void eliminarActividad() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JComboBox<String> actividadesCombo = new JComboBox<>(mapaActividades.keySet().toArray(new String[0]));
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("times new roman", Font.PLAIN, 15));
        
        
        btnEliminar.addActionListener(e -> {
            String nombreActividad = (String) actividadesCombo.getSelectedItem();
            if (nombreActividad != null) {
                mapaActividades.remove(nombreActividad);
                JOptionPane.showMessageDialog(this, "Actividad eliminada con éxito");
                mostrarMenuGestion();
            }
        });
        
        btnVolver.addActionListener(e -> mostrarMenuGestion());
        
        panel.add(new JLabel("Seleccione la actividad a eliminar:"));
        panel.add(actividadesCombo);
        panel.add(btnEliminar);
        panel.add(btnVolver);
        
        cambiarPanel(panel);
    }

    private void cambiarPanel(JPanel nuevoPanel) {
        if (panelActual != null) {
            remove(panelActual);
        }
        panelActual = nuevoPanel;
        add(panelActual);
        revalidate();
        repaint();
    }

    private String actividadToString(Actividad actividad) {
        StringBuilder sb = new StringBuilder();
        sb.append("Actividad: ").append(actividad.getActName()).append("\n");
        
        LocalDate fechaInicio = actividad.getFechaInicio();
        sb.append("Fecha de Inicio: ").append(fechaInicio != null ? fechaInicio.toString() : "No establecida").append("\n");
        
        Encargado encargado = actividad.getEncargado();
        if (encargado != null) {
            sb.append("Encargado: ").append(encargado.getName())
              .append(" | ").append(encargado.getRut()).append("\n");
        } else {
            sb.append("Encargado: No asignado\n");
        }
        
        sb.append("Participantes:\n");
        ArrayList<Participante> participantes = actividad.getParticipantes();
        if (participantes.isEmpty()) {
            sb.append("- No hay participantes registrados\n");
        } else {
            for (Persona p : participantes) {
                sb.append("- ").append(p.getName()).append(" | ").append(p.getRut()).append("\n");
            }
        }
        return sb.toString();
    }
}
