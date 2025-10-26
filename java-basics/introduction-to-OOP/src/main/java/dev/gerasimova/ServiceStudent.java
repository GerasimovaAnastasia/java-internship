package dev.gerasimova;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Сервисный класс для работы с сущностью Student.
 * Предоставляет методы для создания, поиска и отображения студентов.
 * Включает функции для анализа успеваемости и поиска лучших студентов.
 */
public class ServiceStudent {

    /**
     * Создает список студентов и заполняет тестовыми данными.
     * <p>
     * @return новый список студентов.
     */
    public static List<Student> createSampleStudents() {

        Student student1 = new Student("Maria", 20, 4.8);
        Student student2 = new Student("Alex", 21, 4.6);
        Student student3 = new Student("Bob", 20, 4.5);
        ArrayList<Student> list = new ArrayList<>();

        list.add(student1);
        list.add(student2);
        list.add(student3);

        return list;
    }

    /**
     * Выводит список студентов.
     * <p>
     * @param students - список студентов для печати.
     */
    public static void printAllStudents(List<Student> students) {
        validateStudentList(students);
        System.out.println("Список студентов:");
        for(Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * Находит студента с максимальным средним баллом.
     * <p>
     * @param students - список студентов для поиска.
     * @return студент с максимальным средним баллом
     */
    public static Student findStudentWithMaxGrade(List<Student> students) {
        validateStudentList(students);

        return students.stream()
                .max(Comparator.comparingDouble(Student::getAverageGrade))
                .orElseThrow();
    }
    /**
     * Демонстрирует все этапы работы со студентами.
     * Создает список, выводит список студентов, находит и показывает лучшего студента.
     */
    public static void demonstrateStudentWorkflow() {
        List<Student> students = createSampleStudents();
        printAllStudents(students);
        Student bestStudent = findStudentWithMaxGrade(students);

        System.out.printf("Студент с максимальным средним баллом: %s, оценка: %.1f%n",
                bestStudent.getName(), bestStudent.getAverageGrade());
    }
    /**
     * Проверяет список студентов на null и пустоту.
     * @param students список студентов для проверки.
     * @throws IllegalArgumentException если список null или пустой
     */
    public static void validateStudentList(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("Список студентов не может быть пустым или null!");
        }
    }

}
