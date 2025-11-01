package test;

import model.Student;
import service.ServiceStudent;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;

import java.util.ArrayList;
/**
 * Тестовый класс для проверки функциональности Stream API.
 * Содержит тесты для работы с потоками данных студентов.
 *
 * @see Student
 * @see ServiceStudent#findAverageGradeSpecificStudents(ArrayList)
 */
public final class StreamApiTest {

    /**
     * Приватный конструктор.
     */
    private StreamApiTest() {
        throw new UnsupportedOperationException("StreamApiTest class");
    }

    /**
     * Запускает набор тестов для проверки работы с Stream API.
     *
     * @return true если все тесты прошли успешно, false в противном случае
     * @see #shouldFindAverageGradeForSpecificStudents()
     */
    public static boolean runStreamApiTests() {
        String[] descriptions = {
                "Поиск среднего балла студентов старше 20 лет с именем на 'A'"
        };

        TestMethod[] tests = {
                StreamApiTest::shouldFindAverageGradeForSpecificStudents
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 8: Stream API", descriptions, tests);
    }
    /**
     * Тестирует поиск среднего балла для определенной группы студентов.
     * Фильтрует студентов старше 20 лет с именами, начинающимися на 'A',
     * и вычисляет их средний балл.
     *
     * @return true если расчет среднего балла корректен, false в противном случае
     * @see ServiceStudent#createSampleStudents
     * @see ServiceStudent#findAverageGradeSpecificStudents(ArrayList)
     */
    public static boolean shouldFindAverageGradeForSpecificStudents() {
        ArrayList<Student> studentArrayList = ServiceStudent.createSampleStudents();

        double averageGrade = ServiceStudent.findAverageGradeSpecificStudents(studentArrayList);
        boolean testPassed = averageGrade == 4.6;

        System.out.printf("Средний балл: %.1f%n", averageGrade);
        return testPassed;
    }
}
