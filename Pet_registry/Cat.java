package Pet_registry;

public class Cat extends Pet {
    String petType = "Кот/Кошка";

    public Cat() {
    }

    @Override
    public String toString() {
        return this.petType + ", " + super.toString();
    }

}
