package Pet_registry;

import java.util.ArrayList;

public class Commands {
    private ArrayList<String> commands;
    
    public Commands() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    public ArrayList<String> getCommands() {
        return commands;
    }
}
