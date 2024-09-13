public class Persona {
    private String nombre;
    private String rut;
    
    public Persona(String nombre, String rut){
        this.nombre = nombre;
        this.rut = rut;
    }
    
    public String getName() {
        return nombre;
    }
    
    public String getRut() {
        return rut;
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
