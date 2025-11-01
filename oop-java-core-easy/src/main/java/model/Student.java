package model;

import java.util.Objects;

/**
 * Класс, описывающий сущность Student.
 * Представляет студента учебного заведения с именем, возрастом и средним баллом.
 * Наследует от класса {@link Person} и добавляет средний балл студента.
 */
public class Student extends Person {
    private double averageGrade;


    /**
     * Конструктор по умолчанию.
     * Создает объект Student с незаполненными полями.
     */
    public Student() {}

    /**
     * Конструктор с полями: имя и возраст студента.
     * @param name - имя студента.
     * @param age - возраст студента.
     */
    public Student(String name, int age) {

        setName(name);
        setAge(age);
    }

    /**
     * Полный конструктор со всеми полями класса Student.
     * @param name имя студента (не может быть null или пустым)
     * @param age возраст студента (должен быть положительным числом)
     * @param averageGrade средний балл студента (от 0.0 до 10.0)
     * @throws IllegalArgumentException если средний балл не прошел валидацию
     */
    public Student(String name, int age, double averageGrade) {
        setName(name);
        setAge(age);
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
     * Сравнивает этого студента с указанным объектом.
     * Студенты считаются равными, если у них совпадают имя и возраст.
     * @param o - объект для сравнения.
     * @return true если объекты равны, false в противном случае
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getAge() == student.getAge() && Objects.equals(getName(), student.getName());
    }

    /**
     * Возвращает хэш-код для этого студента.
     * Хэш-код рассчитывается на основе имени и возраста в соответствии с контрактом
     * {@link #equals(Object)}.
     *
     * @return хэш-код объекта
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    /**
     * Выводит информацию о студенте в консоль.
     * Демонстрирует переопределение метода для полиморфизма.
     */
    @Override
    public void introduce() {
        System.out.printf("Студент: Имя: %s, Возраст: %d, Средний балл: %.1f\n", getName(),
                getAge(), averageGrade);
    }

}
