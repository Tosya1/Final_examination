-- 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”
DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;
USE human_friends;

-- 8. Создать таблицы с иерархией из диаграммы в БД
-- Таблица с классами животных
DROP TABLE IF EXISTS animal_classes;
CREATE TABLE animal_classes (
	id SERIAL PRIMARY KEY, -- SERIAL = BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
    animal_class VARCHAR(50)
);

INSERT INTO animal_classes (animal_class) VALUES 
('Домашние животные'),
('Вьючные животные');

-- Таблица с подклассами животных
DROP TABLE IF EXISTS animal_subclasses;
CREATE TABLE animal_subclasses (
	id SERIAL PRIMARY KEY, 
	animal_class_id BIGINT UNSIGNED NOT NULL,
    animal_subclass VARCHAR(50),
    FOREIGN KEY (animal_class_id) REFERENCES animal_classes(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO animal_subclasses (animal_class_id, animal_subclass) VALUES 
(1, 'Собаки'),
(1, 'Кошки'),
(1, 'Хомяки'),
(2, 'Лошади'),
(2, 'Ослы'),
(2, 'Верблюды');


-- 9. Заполнить низкоуровневые таблицы именами(животных), командами
-- которые они выполняют и датами рождения

DROP TABLE IF EXISTS dogs;
CREATE TABLE dogs (
	id SERIAL PRIMARY KEY, 
	animal_subclass_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(50),
    commands TEXT,
    date_of_birth DATE,
    FOREIGN KEY (animal_subclass_id) REFERENCES animal_subclasses(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO dogs (animal_subclass_id, name, commands, date_of_birth) VALUES 
(1, 'Шарик', 'Сидеть, лежать, рядом, место, голос', '2020-01-05'),
(1, 'Тузик', 'Сидеть, лежать, рядом, место, голос, служить', '2021-02-23'),
(1, 'Дина', 'Сидеть, лежать, рядом, место, голос, фас, апорт', '2018-10-05'),
(1, 'Ласка', 'Сидеть, лежать, рядом, место, голос, зайка', '2019-01-09');

DROP TABLE IF EXISTS cats;
CREATE TABLE cats (
	id SERIAL PRIMARY KEY, 
	animal_subclass_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(50),
    commands TEXT,
    date_of_birth DATE,
    FOREIGN KEY (animal_subclass_id) REFERENCES animal_subclasses(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO cats (animal_subclass_id, name, commands, date_of_birth) VALUES 
(2, 'Барсик', 'Есть, место, играть', '2022-03-15'),
(2, 'Мурка', 'Есть, место, играть, голос', '2019-06-10'),
(2, 'Мурзик', 'Есть, место, играть', '2021-03-10'),
(2, 'Маргоша', 'Есть, место, играть', '2017-03-08');

DROP TABLE IF EXISTS hamsters;
CREATE TABLE hamsters (
	id SERIAL PRIMARY KEY, 
	animal_subclass_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(50),
    commands TEXT,
    date_of_birth DATE,
    FOREIGN KEY (animal_subclass_id) REFERENCES animal_subclasses(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO hamsters (animal_subclass_id, name, commands, date_of_birth) VALUES 
(3, 'Персик', 'Есть, крутить колесо', '2021-02-23'),
(3, 'Хома', 'Есть, крутить колесо', '2022-06-08'),
(3, 'Буся', 'Есть, крутить колесо', '2022-10-17');

DROP TABLE IF EXISTS horses;
CREATE TABLE horses (
	id SERIAL PRIMARY KEY, 
	animal_subclass_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(50),
    commands TEXT,
    date_of_birth DATE,
    FOREIGN KEY (animal_subclass_id) REFERENCES animal_subclasses(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO horses (animal_subclass_id, name, commands, date_of_birth) VALUES 
(4, 'Изумруд', 'Галоп, шагом, стоять', '2022-04-01'),
(4, 'Пегас', 'Галоп, рысь, стоять', '2020-11-15');

DROP TABLE IF EXISTS donkeys;
CREATE TABLE donkeys (
	id SERIAL PRIMARY KEY, 
	animal_subclass_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(50),
    commands TEXT,
    date_of_birth DATE,
    FOREIGN KEY (animal_subclass_id) REFERENCES animal_subclasses(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO donkeys (animal_subclass_id, name, commands, date_of_birth) VALUES 
(5, 'Иа', 'Идти, стоять', '2020-03-16'),
(5, 'Валера', 'Идти, стоять', '2019-11-12');

DROP TABLE IF EXISTS camels;
CREATE TABLE camels (
	id SERIAL PRIMARY KEY, 
	animal_subclass_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(50),
    commands TEXT,
    date_of_birth DATE,
    FOREIGN KEY (animal_subclass_id) REFERENCES animal_subclasses(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO camels (animal_subclass_id, name, commands, date_of_birth) VALUES 
(6, 'Данат', 'Идти, стоять', '2019-12-08'),
(6, 'Бархан', 'Идти, стоять', '2019-08-22');


-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
-- питомник на зимовку. 
DROP TABLE camels;

-- Объединить таблицы лошади, и ослы в одну таблицу.
CREATE TABLE pack_animals
SELECT * FROM horses 
UNION
SELECT * FROM donkeys;

SELECT * FROM pack_animals;

-- 11. Создать новую таблицу “молодые животные” в которую попадут все
-- животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
-- до месяца подсчитать возраст животных в новой таблице
DROP TABLE IF EXISTS young_animals;
CREATE TABLE young_animals
SELECT * FROM 
(SELECT id, name, commands, date_of_birth, TIMESTAMPDIFF(MONTH,date_of_birth,CURRENT_DATE()) AS age_in_months
FROM cats
UNION 
SELECT id, name, commands, date_of_birth, TIMESTAMPDIFF(MONTH,date_of_birth,CURRENT_DATE()) AS age_in_months
FROM dogs
UNION 
SELECT id, name, commands, date_of_birth, TIMESTAMPDIFF(MONTH,date_of_birth,CURRENT_DATE()) AS age_in_months
FROM hamsters
UNION 
SELECT id, name, commands, date_of_birth, TIMESTAMPDIFF(MONTH,date_of_birth,CURRENT_DATE()) AS age_in_months
FROM pack_animals)AS animals
WHERE date_of_birth > DATE_SUB(CURRENT_DATE(), INTERVAL 3 YEAR) AND date_of_birth < DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR);

SELECT * FROM young_animals;

-- 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
-- прошлую принадлежность к старым таблицам.
CREATE TABLE animals
SELECT sp.id, sp.animal_subclass_id, sp.name, sp.commands, sp.date_of_birth, sc.animal_subclass, ac.animal_class 
FROM  
(SELECT * FROM cats
UNION 
SELECT * FROM dogs
UNION 
SELECT * FROM hamsters
UNION 
SELECT * FROM pack_animals) AS sp
JOIN animal_subclasses sc ON sc.id = sp.animal_subclass_id
JOIN animal_classes ac ON ac.id = sc.animal_class_id;

SELECT * FROM animals;
