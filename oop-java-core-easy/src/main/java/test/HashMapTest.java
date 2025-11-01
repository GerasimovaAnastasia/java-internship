package test;

import exceptions.StudentNotFoundException;
import model.Student;
import service.ServiceStudent;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;
/**
 * Тестовый класс для проверки работы с HashMap в сервисе студентов.
 * Содержит тесты для основных операций с HashMap: добавление, поиск и удаление студентов.
 *
 * @see ServiceStudent
 * @see Student
 * @see StudentNotFoundException
 * @see TestRunnerUtil
 */
public final class HashMapTest {

    /**
     * Приватный конструктор.
     */
    private HashMapTest() {
        throw new UnsupportedOperationException("HashMapTest class");
    }

    /**
     * Запускает все тесты для задачи по работе с HashMap.
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #shouldAddAndFindStudentInHashMap()
     * @see #shouldRemoveAndFindStudentInHashMap()
     */
    public static boolean runHashMapTests() {
        String[] descriptions = {
                "Добавление и поиск студентов в HashMap",
                "Удаление студентов из HashMap"
        };

        TestMethod[] tests = {
                HashMapTest::shouldAddAndFindStudentInHashMap,
                HashMapTest::shouldRemoveAndFindStudentInHashMap
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 7: Работа с HashMap", descriptions, tests);
    }
    /**
     * Проверяет корректность добавления студентов в HashMap и их последующего поиска.
     * Тестирует методы addStudent() и findStudent() сервиса ServiceStudent.
     *
     * @return true если студенты успешно добавлены и найдены, false в противном случае
     * @see ServiceStudent#addStudent(String, Student)
     * @see ServiceStudent#findStudent(String)
     */
    public static boolean shouldAddAndFindStudentInHashMap() {

        ServiceStudent serviceStudent = new ServiceStudent();

        serviceStudent.addStudent("S011", new Student("Maria", 20, 4.8));
        serviceStudent.addStudent("S002", new Student("Alex", 21, 4.5));

        int size = serviceStudent.getStudents().size();

        Student maria = serviceStudent.findStudent("S011");
        Student alex = serviceStudent.findStudent("S002");

        boolean mariaCorrect = maria != null && "Maria".equals(maria.getName());
        boolean alexCorrect = alex != null && "Alex".equals(alex.getName());

        System.out.println("Добавлено студентов: " + size);
        System.out.println("Maria найдена: " + mariaCorrect);
        System.out.println("Alex найден: " + alexCorrect);

        return size == 2 && mariaCorrect && alexCorrect;
    }
    /**
     * Проверяет корректность удаления студентов из HashMap.
     * Тестирует метод removeStudent() и проверяет, что после удаления студенты больше не находятся.
     *
     * @return true если студенты успешно удалены и больше не находятся, false в противном случае
     * @see ServiceStudent#removeStudent(String)
     * @see ServiceStudent#findStudent(String)
     * @see StudentNotFoundException
     */
    public static boolean shouldRemoveAndFindStudentInHashMap() {
        ServiceStudent serviceStudent = new ServiceStudent();

        serviceStudent.addStudent("S011", new Student("Maria", 20, 4.8));
        serviceStudent.addStudent("S002", new Student("Alex", 21, 4.5));

        serviceStudent.removeStudent("S011");
        serviceStudent.removeStudent("S002");

        int size = serviceStudent.getStudents().size();

        boolean mariaNotFound = false;
        boolean alexNotFound = false;

        try {
            serviceStudent.findStudent("S011");
        } catch (StudentNotFoundException e) {
            mariaNotFound = true;
        }

        try {
            serviceStudent.findStudent("S002");
        } catch (StudentNotFoundException e) {
            alexNotFound = true;
        }

        System.out.println("Осталось студентов: " + size);
        System.out.println("Maria не найдена: " + mariaNotFound);
        System.out.println("Alex не найден: " + alexNotFound);

        return size == 0 && mariaNotFound && alexNotFound;
    }
}
