package Pet_registry;

import java.util.HashMap;

public class App {

    private static Integer idCounter = 1;
    private Counter counter;
    private Registry<Pet> registry;
    private HashMap<String, String> petTypes;
    private HashMap<String, Pet> petCtors;
    private HashMap<String, String> actions;
    private HashMap<String, String> confirmation;

    public App() {
        this.registry = new Registry<>();
        this.counter = new Counter();
    }

    // supporting functions
    private static String getProperty(String msg) {
        UI.showInfo(msg);
        return UI.getInfo();
    }

    private static boolean checkValue(String value, String regex, String errMsg) {
        if (!value.matches(regex)) {
            UI.showInfo(errMsg);
            return false;
        } else {
            return true;
        }
    }

    private static String getName() {
        String name = getProperty(Constants.PET_NAME_MSG);
        if (!checkValue(name, Constants.NAME_REGEX, Constants.INPUT_ERR)) {
            name = getName();
        }
        return name;
    }

    private static String getBreed() {
        String breed = getProperty(Constants.PET_BREED_MSG);
        if (breed.isEmpty() || breed.equals(" ") || breed == null) {
            breed = "Нет данных";
        }
        return breed;
    }

    private static String getDateOfBirth() {
        String dateOfBirth = getProperty(Constants.PET_BIRTHDATE_MSG);
        if (!checkValue(dateOfBirth, Constants.BIRTHDATE_REGEX, Constants.INPUT_ERR)) {
            dateOfBirth = getDateOfBirth();
        }
        return dateOfBirth;
    }

    private Pet getPet() {
        return this.petCtors.get(getInputVal(this.petTypes, Constants.PET_TYPE_MSG));
    }

    private static String getCommand() {
        String command = getProperty(Constants.PET_CMD_MSG);
        if (command.isEmpty() || command.equals(" ") || command == null) {
            UI.showInfo(Constants.INPUT_ERR);
            command = getCommand();
        }
        return command;
    }

    private void createPet(Pet pet) {
        pet.setName(getName());
        pet.setBreed(getBreed());
        pet.setDateOfBirth(getDateOfBirth());
        pet.setAge(pet.calculateAge());
        pet.setId(idCounter);
        pet.setCommands(new Commands());
        idCounter++;
    }

    private void askToContinue() {
        String key = getInputVal(confirmation, Constants.PET_CONTINUE_MSG);
        if (key.equals("1")) {
            chooseAction();
        } else {
            System.exit(0);
        }

    }

    private static String getInputVal(HashMap<String, String> map, String msg) {
        UI.showInfo(msg);
        UI.printMap(map);
        String key = UI.getInfo();
        if (!map.containsKey(key)) {
            UI.showInfo(Constants.INPUT_ERR);
            key = getInputVal(map, msg);
        }
        return key;
    }

    private boolean checkId(String id) {
        boolean res = false;
        for (Pet pet : this.registry.getRegistry()) {
            if (id.equals(Integer.toString(pet.getId()))) {
                res = true;
            }
        }
        return res;
    }

    private String getPetId() {
        UI.showInfo(Constants.INPUT_PET_ID);
        String inputId = UI.getInfo();
        if (!checkId(inputId)) {
            UI.showInfo(Constants.INPUT_ERR);
            inputId = getPetId();
        }
        return inputId;
    }

    private boolean checkCommand(String cmd, Pet pet) {
        boolean res = false;
        for (String command : pet.getCommands().getCommands()) {
            if (command.equalsIgnoreCase(cmd)) {
                res = true;
            }
        }
        return res;
    }

    // main commands
    private void createRegEntry() {
        Pet newPet = getPet();
        createPet(newPet);
        this.registry.addPet(newPet);
        this.counter.add();
        UI.showInfo(Constants.PET_NOTE_SUCCESS);
        UI.showInfoInt(Constants.PET_COUNT_MSG, this.counter.getCounter());
        askToContinue();
    }

    private void showPetList() {
        if (this.registry.getRegistry().size() == 0) {
            UI.showInfo(Constants.EMPTY_LIST);
        } else {
            UI.showInfo(Constants.DELIMITER);
            for (Pet pet : this.registry.getRegistry()) {
                UI.showInfo(pet.toString());
            }
        }
        UI.showInfo(Constants.DELIMITER);
        askToContinue();
    }

    private void addPetCommand() {
        String petId = getPetId();
        String command = getCommand();
        for (Pet pet : this.registry.getRegistry()) {
            if (petId.equals(Integer.toString(pet.getId())) && !checkCommand(command, pet)) {
                pet.getCommands().addCommand(command);
                UI.showInfo(Constants.PET_CMD_SUCCESS);
                askToContinue();
            } else {
                UI.showInfo(Constants.PET_CMD_DUPLICATE);
                askToContinue();
            }
        }

    }

    private void showPetCommands() {
        String petId = getPetId();
        for (Pet pet : this.registry.getRegistry()) {
            if (petId.equals(Integer.toString(pet.getId()))) {
                UI.showInfo(Constants.DELIMITER);
                UI.showInfoStr(Constants.PET_CMD_LIST, pet.getName());
                for (String command : pet.getCommands().getCommands()) {
                    UI.showInfo(command);
                }
                UI.showInfo(Constants.DELIMITER);
            }
        }
        askToContinue();
    }

    private void chooseAction() {
        String actKey = getInputVal(actions, Constants.PET_ACT_MSG);
        if (actKey.equals("1")) {
            createRegEntry();
        } else if (actKey.equals("2")) {
            showPetList();
        } else if (actKey.equals("3")) {
            addPetCommand();
        } else if (actKey.equals("4")) {
            showPetCommands();
        }
    }

    public void run() {
        HashMap<String, String> petTypes = new HashMap<>();
        petTypes.put("1", "Кошка");
        petTypes.put("2", "Собака");
        petTypes.put("3", "Хомяк");

        HashMap<String, Pet> petСtors = new HashMap<>();
        petСtors.put("1", new Cat());
        petСtors.put("2", new Dog());
        petСtors.put("3", new Hamster());

        HashMap<String, String> actions = new HashMap<>();
        actions.put("1", "Завести нового питомца");
        actions.put("2", "Просмотреть список питомцев");
        actions.put("3", "Добавить новую команду, которую научился выполнять питомец");
        actions.put("4", "Просмотреть список команд, которые умеет выполнять питомец");

        HashMap<String, String> confirmation = new HashMap<>();
        confirmation.put("1", "Да");
        confirmation.put("2", "Нет");

        this.petTypes = petTypes;
        this.petCtors = petСtors;
        this.actions = actions;
        this.confirmation = confirmation;

        chooseAction();
    }

}
