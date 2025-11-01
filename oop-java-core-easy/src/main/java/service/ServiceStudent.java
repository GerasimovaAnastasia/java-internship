package service;

import exceptions.StudentNotFoundException;
import model.Student;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Сервисный класс для работы с сущностью Student.
 * Предоставляет методы для управления студентами: создание, поиск, удаление, сортировка.
 * Включает функции для анализа успеваемости и работы с файлами.
 *
 * @see Student
 * @see StudentNotFoundException
 */
public class ServiceStudent {

    /**
     * Создает список студентов и заполняет тестовыми данными.
     * <p>
     * @return новый список студентов.
     */
    public static ArrayList<Student> createSampleStudents() {

        Student student1 = new Student("Maria", 20, 4.8);
        Student student2 = new Student("Alex", 21, 4.6);
        Student student3 = new Student("Alex", 20, 4.5);
        Student student4 = new Student("Elena", 20, 4.9);
        Student student5 = new Student("John", 21, 4.3);
        ArrayList<Student> list = new ArrayList<>();

        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);

        return list;
    }

    /**
     * Проверяет список студентов на null и пустоту.
     * @param students список студентов для проверки.
     * @throws IllegalArgumentException если список null или пустой
     */
    public static void validateStudentList(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("Список студентов не может быть пустым или null!");
        }
    }
    /**
     * Сортирует список студентов по имени (в алфавитном порядке) и среднему баллу (по убыванию).
     *
     * @param students список студентов для сортировки
     * @return новый отсортированный список студентов
     * @throws IllegalArgumentException если список студентов null или пустой
     */
    public static ArrayList<Student> sortStudentsList(ArrayList<Student> students) {
        validateStudentList(students);
        ArrayList<Student> list = new ArrayList<>(students);
        list.sort(Comparator.comparing(Student::getName)
                .thenComparing(Comparator.comparing(Student::getAverageGrade).reversed()));
        return list;
    }

}
