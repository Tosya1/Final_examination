package Pet_registry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pet {
    private Integer id;
    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private Integer age;
    private Commands commands;

    public Pet() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = convertDate(dateOfBirth);
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public Integer calculateAge() {
        try {
            return LocalDate.now().compareTo(this.dateOfBirth);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public Commands getCommands() {
        return commands;
    }

    private static LocalDate convertDate(String date) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate bd = LocalDate.parse(date, dtf);
            return bd;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", имя: " + this.name + ", порода: " + this.breed + ", дата рождения: "
                + this.dateOfBirth + ", возраст(лет): " + this.age;
    }
}
