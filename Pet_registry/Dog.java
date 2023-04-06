package Pet_registry;

public class Dog extends Pet {
    String petType = "Собака";

    public Dog() {
    }

    @Override
    public String toString() {
        return this.petType + ", " + super.toString();
    }

}
