package Pet_registry;


public class Constants {
    public static final String PET_ACT_MSG = "Укажите число, которое соответствует желаемой операции:";
    public static final String PET_TYPE_MSG = "Укажите число, которое соответствует типу питомца:";
    public static final String PET_NOTE_SUCCESS = "Запись данных о новом питомце прошла успешно.";
    public static final String PET_COUNT_MSG = "Общее количество питомцев: %d.\n";
    public static final String PET_CONTINUE_MSG = "Продолжить работу?";
    public static final String PET_NAME_MSG = "Укажите имя питомца (должно начинаться с заглавной буквы): ";
    public static final String PET_BREED_MSG = "Укажите породу питомца: ";
    public static final String PET_BIRTHDATE_MSG = "Укажите дату рождения питомца в формате \"дд.мм.гггг\": ";
    public static final String PET_CMD_MSG = "Укажите командy, которую научился выполнять питомец: ";
    public static final String INPUT_PET_ID = "Укажите id питомца: ";
    public static final String PET_CMD_SUCCESS = "Новая команда успешно добавлена.";
    public static final String PET_CMD_DUPLICATE = "Такая команда уже есть!";
    public static final String PET_CMD_LIST = "%s умеет выполнять следующие команды:\n"; 
    public static final String INPUT_ERR = "Вы ввели некорректные данные. Пожалуйста, повторите попытку.";
    public static final String NAME_REGEX = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
    public static final String BIRTHDATE_REGEX = "(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](19|20)\\d\\d";
    public static final String EMPTY_LIST = "Список питомцев пуст.";
    public static final String DELIMITER = "-----------------------------------------------------------------------------------------------------------";

}
