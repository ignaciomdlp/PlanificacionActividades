public class Encargado extends Persona {
    public Encargado(String nombre, String rut)
    {
        super(nombre, rut);
    }

    @Override
    public String getName() {
        return "(P)";
    }
}
