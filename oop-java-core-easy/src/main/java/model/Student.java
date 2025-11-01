package model;

/**
 * Класс, описывающий сущность Student.
 * Представляет студента учебного заведения с именем, возрастом и средним баллом.
 */
public class Student {

    private String name;
    private int age;
    private double averageGrade;

    /**
     * Полный конструктор со всеми полями класса Student.
     * @param name имя студента (не может быть null или пустым)
     * @param age возраст студента (должен быть положительным числом)
     * @param averageGrade средний балл студента (от 0.0 до 10.0)
     * @throws IllegalArgumentException если средний балл не прошел валидацию
     */
    public Student(String name, int age, double averageGrade) {
        this.name = name;
        this.age = age;
        setAverageGrade(averageGrade);
    }

    /**
     * Возвращает средний балл студента.
     *
     * @return средний балл в диапазоне от 0.0 до 10.0
     */
    public double getAverageGrade() {
        return averageGrade;
    }

     /**
     * Устанавливает средний балл студента с валидацией.
     *
     * @param averageGrade средний балл студента (от 0.0 до 10.0 включительно)
     * @throws IllegalArgumentException если балл меньше 0 или больше 10
     * @throws IllegalArgumentException - если входные данные не прошли валидацию.
     */
    public void setAverageGrade(double averageGrade) {

        if (averageGrade >= 0 && averageGrade <= 10) {

            this.averageGrade = averageGrade;
        } else {
            throw new IllegalArgumentException("Балл должен быть в пределах от 0 до 10 включительно!");
        }
    }
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
     * Выводит информацию о студенте в консоль.
     */
    @Override
    public String toString() {
        return String.format("Студент: %s, возраст: %d, средний балл: %.1f",
                name, age, averageGrade);
    }


}
