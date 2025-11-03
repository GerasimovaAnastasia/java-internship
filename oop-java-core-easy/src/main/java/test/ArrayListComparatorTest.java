package test;

import model.Student;
import service.ServiceStudent;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;
import java.util.List;

/**
 * Тестовый класс для проверки функциональности ArrayList и Comparator.
 * Содержит тесты для проверки корректности сортировки студентов.
 *
 * @see Student
 * @see ServiceStudent
 * @see TestRunnerUtil
 */
public final class ArrayListComparatorTest {

    /**
     * Приватный конструктор.
     */
    private ArrayListComparatorTest() {
        throw new UnsupportedOperationException("ArrayListComparatorTest class");
    }

    /**
     * Запускает все тесты для задачи по ArrayList и Comparator.
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #isCorrectlySorted()
     */
    public static boolean runArrayListComparatorTests() {

        String[] descriptions = {
                "Сортировка по возрасту и среднему баллу"
        };

        TestMethod[] tests = {
                ArrayListComparatorTest::isCorrectlySorted
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 4: ArrayList и Comparator", descriptions, tests);

    }
    /**
     * Проверяет корректность сортировки списка студентов.
     * Сортировка должна выполняться по имени (в алфавитном порядке)
     * и среднему баллу (по убыванию) для студентов с одинаковыми именами.
     *
     * @return true если сортировка выполнена корректно, false в противном случае
     * @see ServiceStudent#sortStudentsList(List)
     * @see ServiceStudent#createSampleStudents()
     */
    public static boolean isCorrectlySorted() {

        List<Student> studentList = ServiceStudent.createSampleStudents();
        List<Student> sortedList = ServiceStudent.sortStudentsList(studentList);
        for (int i = 0; i < sortedList.size() - 1; i++) {
            Student st1 = sortedList.get(i);
            Student st2 = sortedList.get(i + 1);

            int nameCompare = st1.getName().compareTo(st2.getName());

            if (nameCompare == 0) {
                if (st1.getAverageGrade() < st2.getAverageGrade()) {
                    System.out.println("Ошибка: баллы должны идти по убыванию!");
                    return false;
                }
            } else if (nameCompare > 0) {
                System.out.println("Ошибка: имена должны идти в алфавитном порядке!");
                return false;
            }
        }
        System.out.println("Сортировка выполнена корректно.");
        return true;
    }

}
