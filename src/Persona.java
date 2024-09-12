public class Persona {
    private String nombre;
    private String rut;
    private String cargo;
    
    public Persona(String nombre, String rut, String cargo){
        this.nombre = nombre;
        this.rut = rut;
        this.cargo = cargo;
    }

    public Persona(String nombre, String rut) {
        this(nombre, rut, "Participante");
    }
    
    public String getName() {
        return nombre;
    }
    
    public String getRut() {
        return rut;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public void setName(String a) {
        nombre = a;
    }
    
    public void setRut(String b) {
        rut = b;
    }
    
    public void mostrarInfo() {
        System.out.println(nombre + "\n" + rut);
    }
}
