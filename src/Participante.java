public class Participante extends Persona {
    public Participante(String nombre, String rut) {
        super(nombre, rut);
    }

    @Override
    public String presentarse() {
        return "- (P) " + getName() + " - " + getRut();
    }
}
