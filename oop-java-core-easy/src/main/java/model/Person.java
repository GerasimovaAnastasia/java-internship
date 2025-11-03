package model;
/**
 * Базовый класс, описывающий сущность Person.
 * Представляет человека с именем и возрастом.
 * Служит родительским классом для более специализированных сущностей, таких как Student и Teacher.
 * @see Student
 * @see Teacher
 */
 public class Person {
    private String name;
    private int age;

    /**
     * Возвращает имя человека.
     *
     * @return имя человека
     */
    public String getName() {
        return name;
    }
    /**
     * Устанавливает имя человека.
     *
     * @param name имя человека
     * @throws IllegalArgumentException если имя равно null или пустое
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        this.name = name;
    }
    /**
     * Возвращает возраст человека.
     *
     * @return возраст человека
     */
    public int getAge() {
        return age;
    }
    /**
     * Устанавливает возраст человека.
     *
     * @param age возраст человека
     * @throws IllegalArgumentException если возраст отрицательный
     */
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.age = age;
    }
    /**
     * Выводит базовую информацию о человеке в консоль.
     * Метод предназначен для переопределения в дочерних классах для демонстрации полиморфизма.
     *
     * @see Student#introduce()
     * @see Teacher#introduce()
     */
    public void introduce() {
        System.out.printf("Имя: %s, Возраст: %d\n", name, age);
    }
}
