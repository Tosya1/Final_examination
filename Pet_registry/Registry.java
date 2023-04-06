package Pet_registry;

import java.util.ArrayList;

public class  Registry <T extends Pet>{

    private ArrayList<T> registry;
    public Registry() {
        this.registry = new ArrayList<>();
    }

    public ArrayList<T> getRegistry() {
        return registry;
    }

    public void addPet(T pet) {
        registry.add(pet);
    }


}
