package test;

import model.Student;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;
/**
 * Тестовый класс для проверки инкапсуляции и валидации данных в классе Student.
 * Содержит тесты для проверки корректной работы сеттера среднего балла с валидацией.
 *
 * @see Student
 * @see TestRunnerUtil
 */
public final class EncapsulationTest {

    /**
     * Приватный конструктор.
     */
    private EncapsulationTest() {
        throw new UnsupportedOperationException("EncapsulationTest class");
    }

    /**
     * Запускает все тесты для задачи по инкапсуляции и валидации.
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #shouldAcceptValidNumbers()
     * @see #shouldThrowExceptionWhenGradeIsTooHigh()
     * @see #shouldThrowExceptionWhenGradeIsTooLow()
     */
    public static boolean runEncapsulationTests() {
        String[] descriptions = {
                "Принимает корректные оценки (0-10)",
                "Бросает исключение при оценке > 10",
                "Бросает исключение при отрицательной оценке"
        };

        TestMethod[] tests = {
                EncapsulationTest::shouldAcceptValidNumbers,
                EncapsulationTest::shouldThrowExceptionWhenGradeIsTooHigh,
                EncapsulationTest::shouldThrowExceptionWhenGradeIsTooLow
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 1: Инкапсуляция и валидация", descriptions, tests);
    }
    /**
     * Проверяет, что сеттер принимает корректные значения оценок в диапазоне от 0 до 10.
     *
     * @return true если оценка 10 принята без исключения, false в противном случае
     * @see Student#setAverageGrade(double)
     */
    public static boolean shouldAcceptValidNumbers() {


        return testGrade(10, true);
    }
    /**
     * Проверяет, что сеттер выбрасывает исключение при попытке установить оценку больше 10.
     *
     * @return true если исключение выброшено корректно, false в противном случае
     * @see Student#setAverageGrade(double)
     */
    public static boolean shouldThrowExceptionWhenGradeIsTooHigh() {

        return testGrade(11, false);
    }
    /**
     * Проверяет, что сеттер выбрасывает исключение при попытке установить отрицательную оценку.
     *
     * @return true если исключение выброшено корректно, false в противном случае
     * @see Student#setAverageGrade(double)
     */
    public static boolean shouldThrowExceptionWhenGradeIsTooLow() {
        return testGrade(-1, false);
    }
    /**
     * Вспомогательный метод для тестирования валидации оценок.
     * Проверяет, что оценка либо принимается, либо отвергается в соответствии с ожиданиями.
     *
     * @param grade оценка для тестирования
     * @param shouldSucceed true если оценка должна быть принята, false если должно быть выброшено исключение
     * @return true если результат соответствует ожиданиям, false в противном случае
     * @see Student#setAverageGrade(double)
     */
    public static boolean testGrade(double grade, boolean shouldSucceed) {

        Student student = new Student("Maria", 21, 4.9);

        try {
            student.setAverageGrade(grade);
            System.out.println("Введенная оценка: " + 10);
            return shouldSucceed;
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return !shouldSucceed;
        }
    }
}