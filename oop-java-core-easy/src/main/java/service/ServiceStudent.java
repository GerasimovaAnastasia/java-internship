package service;

import exceptions.StudentNotFoundException;
import model.Student;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Сервисный класс для работы с сущностью Student.
 * Предоставляет методы для управления студентами: создание, поиск, удаление, сортировка.
 * Включает функции для анализа успеваемости и работы с файлами.
 *
 * @see Student
 * @see StudentNotFoundException
 */
public class ServiceStudent {
    private final Map<String, Student> students = new HashMap<>();
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
     * Добавляет студента в хранилище.
     *
     * @param studentId уникальный идентификатор студента
     * @param student объект студента для добавления
     * @throws IllegalArgumentException если studentId или student равны null
     */
    public void addStudent(String studentId, Student student) {
        if (studentId == null || student == null) {
            throw new IllegalArgumentException("Id студента и student не могут быть null");
        }
        students.put(studentId, student);
        System.out.println("Студент добавлен: " + studentId);
    }
    /**
     * Находит студента по идентификатору.
     *
     * @param studentId идентификатор студента для поиска
     * @return найденный объект Student
     * @throws StudentNotFoundException если студент с указанным ID не найден
     * @throws IllegalArgumentException если studentId равен null
     */
    public Student findStudent(String studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Id студента не может быть null");
        }
        Student student = students.get(studentId);
        if (student == null) {
            throw new StudentNotFoundException(studentId);
        }
        return student;
    }
    /**
     * Удаляет студента по идентификатору.
     *
     * @param studentId идентификатор студента для удаления
     * @throws StudentNotFoundException если студент с указанным ID не найден
     * @throws IllegalArgumentException если studentId равен null
     */
    public void removeStudent(String studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Id студента не может быть null");
        }
        Student removed = students.remove(studentId);
        if (removed == null) {
            throw new StudentNotFoundException(studentId);
        }
        System.out.println("Студент удален: " + studentId);
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
    /**
     * Находит средний балл студентов старше 20 лет, чьи имена начинаются на букву 'A'.
     * Использует Stream API для фильтрации и вычислений.
     *
     * @param students список студентов для анализа
     * @return средний балл студентов или 0.0 если таких студентов нет
     * @throws IllegalArgumentException если список студентов null или пустой
     */
    public static double findAverageGradeSpecificStudents(ArrayList<Student> students) {
        validateStudentList(students);
        return (students.stream()
                .filter(student -> student.getAge() > 20 && student.getName().startsWith("A"))
                .mapToDouble(Student::getAverageGrade)
                .average()
                .orElse(0.0));
    }
    /**
     * Читает студентов из файла.
     * Каждая строка файла должна содержать данные в формате: "имя, возраст, средний_балл"
     *
     * @param filePath путь к файлу с данными студентов
     * @return список студентов, прочитанных из файла
     * @throws IllegalArgumentException если filePath равен null или пустой
     */
    public static List<Student> readFromFileStudents(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath не может быть null или пустым");
        }
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                if (student != null) {
                    students.add(student);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка загрузки студентов: " + e.getMessage());
        }
        return students;

    }
    /**
     * Парсит строку с данными студента.
     * Ожидает формат: "имя, возраст, средний_балл"
     *
     * @param line строка для парсингa
     * @return объект Student или null если строка некорректна
     */
    private static Student parseStudent(String line) {
        try {

            String[] parts = line.split(",");
            String name = parts[0].trim();
            int age = Integer.parseInt(parts[1].trim());
            double grade = Double.parseDouble(parts[2].trim());

            return new Student(name, age, grade);

        } catch (Exception e) {
            System.out.println("Ошибка парсинга строки: " + line);
            return null;
        }
    }
    /**
     * Создает тестовый файл с данными студентов.
     *
     * @param filename имя файла для создания
     * @throws IllegalArgumentException если filename равен null или пустой
     */
    public static void createTestFile(String filename) {
        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("filename не может быть null или пустым");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Maria,20,4.8");
            writer.println("Alex,21,4.5");
            writer.println("John,22,4.9");
        } catch (IOException e) {
            System.out.println("Ошибка создания тестового файла");
        }
    }
    /**
     * Возвращает карту всех студентов.
     *
     * @return карта студентов (ключ - идентификатор, значение - студент)
     */
    public Map<String, Student> getStudents() {
        return students;
    }
}
