package model;
/**
 * Класс, описывающий сущность Teacher.
 * Представляет преподавателя учебного заведения с именем, возрастом и должностью.
 * Наследует от класса {@link Person} и добавляет должность преподавателя.
 *
 * @see Person
 * @see Student
 */
public class Teacher extends Person {

    private final String post;

    /**
     * Конструктор преподавателя с именем, возрастом и должностью.
     *
     * @param name имя преподавателя (не может быть null или пустым)
     * @param age возраст преподавателя (должен быть положительным числом)
     * @param post должность преподавателя (не может быть null или пустой)
     * @throws IllegalArgumentException если параметры не прошли валидацию
     */

    public Teacher(String name, int age, String post) {
        setName(name);
        setAge(age);
        if (post == null || post.isBlank()) {
            throw new IllegalArgumentException("Должность не может быть null или пустой");
        }
        this.post = post;
    }
    /**
     * Выводит полную информацию о преподавателе в консоль.
     * Демонстрирует переопределение метода для полиморфизма.
     */
    @Override
    public void introduce() {
        System.out.printf("Преподаватель: Имя: %s, Возраст: %d, Пост: %s\n", getName(),
                getAge(), post);
    }
}
