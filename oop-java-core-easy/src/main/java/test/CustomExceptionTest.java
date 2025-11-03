package test;

import exceptions.StudentNotFoundException;
import service.ServiceStudent;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;
/**
 * Тестовый класс для проверки работы пользовательского исключения StudentNotFoundException.
 * Содержит тесты для проверки корректного выбрасывания исключения в различных сценариях.
 *
 * @see StudentNotFoundException
 * @see ServiceStudent
 * @see TestRunnerUtil
 */
public final class CustomExceptionTest {

    /**
     * Приватный конструктор.
     */
    private CustomExceptionTest() {
        throw new UnsupportedOperationException("CustomExceptionTest class");
    }

    /**
     * Запускает все тесты для задачи по пользовательским исключениям.
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #shouldThrowStudentNotFoundExceptionWhenStudentNotFound()
     * @see #shouldThrowStudentNotFoundExceptionWhenRemovingNonExistent()
     */
    public static boolean runCustomExceptionTests() {
        String[] descriptions = {
                "Исключение при поиске несуществующего студента",
                "Исключение при удалении несуществующего студента"
        };

        TestMethod[] tests = {
                CustomExceptionTest::shouldThrowStudentNotFoundExceptionWhenStudentNotFound,
                CustomExceptionTest::shouldThrowStudentNotFoundExceptionWhenRemovingNonExistent
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 10: Собственное исключение", descriptions, tests);
    }
    /**
     * Проверяет, что при поиске несуществующего студента выбрасывается StudentNotFoundException.
     * Также проверяет, что сообщение исключения содержит ID ненайденного студента.
     *
     * @return true если исключение выброшено корректно и сообщение содержит ID, false в противном случае
     * @see ServiceStudent#findStudent(String)
     * @see StudentNotFoundException
     */
    public static boolean shouldThrowStudentNotFoundExceptionWhenStudentNotFound() {
        ServiceStudent service = new ServiceStudent();

        try {
            service.findStudent("NON_EXISTENT_ID");
            System.out.println("Исключение не было выброшено!");
            return false;

        } catch (StudentNotFoundException e) {
            System.out.println("Исключение выброшено корректно: " + e.getMessage());
            boolean messageCorrect = e.getMessage().contains("NON_EXISTENT_ID");
            System.out.println("Сообщение содержит ID: " + messageCorrect);
            return messageCorrect;
        }
    }
    /**
     * Проверяет, что при удалении несуществующего студента выбрасывается StudentNotFoundException.
     * Также проверяет, что сообщение исключения содержит ID ненайденного студента.
     *
     * @return true если исключение выброшено корректно и сообщение содержит ID, false в противном случае
     * @see ServiceStudent#removeStudent(String)
     * @see StudentNotFoundException
     */
    public static boolean shouldThrowStudentNotFoundExceptionWhenRemovingNonExistent() {
        ServiceStudent service = new ServiceStudent();

        try {
            service.removeStudent("UNKNOWN_ID");
            System.out.println("Исключение не было выброшено!");
            return false;

        } catch (StudentNotFoundException e) {
            System.out.println("Исключение выброшено корректно: " + e.getMessage());
            boolean messageCorrect = e.getMessage().contains("UNKNOWN_ID");
            System.out.println("Сообщение содержит ID: " + messageCorrect);
            return messageCorrect;
        }
    }
}
