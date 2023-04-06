package Pet_registry;

public class Hamster extends Pet {
    String petType = "Хомяк";

    public Hamster() {
    }

    @Override
    public String toString() {
        return this.petType + ", " + super.toString();
    }
}
