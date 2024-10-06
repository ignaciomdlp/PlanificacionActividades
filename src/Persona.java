public class Persona {
    private String nombre;
    private String rut;
    
    public Persona(String nombre, String rut) 
    {
        this.nombre = nombre;
        validarRut(rut);
        this.rut = rut;
    }
    
    private void validarRut(String rut) {
        if (rut == null || !rut.matches("\\d{8}-[\\dkK]")) {
            throw new IllegalArgumentException("Formato de RUT inválido. Debe ser en el formato 00000000-0.");
        }
    }

    public String presentarse() {
        return nombre + " - " + rut;
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
