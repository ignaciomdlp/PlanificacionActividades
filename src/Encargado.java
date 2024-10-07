public class Encargado extends Persona {
    public Encargado(String nombre, String rut)
    {
        super(nombre, rut);
    }

    @Override
    public String presentarse() {
        return "(E) " + getName() + " - " + getRut();
    }

    public String presentarse(String string) {
        return string + " " + getName() + " - " + getRut();
    }
}
