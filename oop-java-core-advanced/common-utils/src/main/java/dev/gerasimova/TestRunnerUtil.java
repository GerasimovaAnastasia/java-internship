package dev.gerasimova;

/**
 * Утилитный класс для запуска и управления тестами.
 * Предоставляет методы для выполнения отдельных тестов и наборов тестов.
 * Поддерживает как тесты с возвращаемым значением, так и void-тесты.
 *
 * @see TestMethod
 * @see VoidTestMethod
 */
public final class TestRunnerUtil {

    /**
     * Приватный конструктор для предотвращения инстанциирования utility-класса.
     */
    private TestRunnerUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Выполняет один тест с возвращаемым boolean значением.
     *
     * @param testNumber номер теста (для отображения)
     * @param description описание теста
     * @param test функциональный интерфейс с тестом для выполнения
     * @return true если тест пройден, false в противном случае
     * @throws IllegalArgumentException если testNumber меньше 1 или description/test равны null
     */
    public static boolean runTest(int testNumber, String description, TestMethod test) {
        if (testNumber < 1) {
            throw new IllegalArgumentException("Номер теста должен быть положительным: " + testNumber);
        }
        if (description == null || test == null) {
            throw new IllegalArgumentException("Описание и тест не могут быть null");
        }
        System.out.printf("\nТест %d: %s\n", testNumber, description);
        boolean result = test.run();
        System.out.println(result ? "Пройден" : "Не пройден");
        return result;
    }
    /**
     * Выполняет набор тестов с возвращаемыми boolean значениями.
     *
     * @param taskName название задачи/набора тестов
     * @param descriptions массив описаний тестов
     * @param tests массив тестов для выполнения
     * @throws IllegalArgumentException если arrays не совпадают по длине или содержат null
     */
    public static void runTestSuite(String taskName, String[] descriptions, TestMethod[] tests) {
        if (taskName == null) {
            throw new IllegalArgumentException("Название задачи не может быть null");
        }
        System.out.println("\n" + "=".repeat(50));
        System.out.println(taskName);
        System.out.println("=".repeat(50));

        if (descriptions.length != tests.length) {
            throw new IllegalArgumentException("Ошибка: количество описаний не совпадает с количеством тестов");
        }

        int passedTests = 0;

        for (int i = 0; i < tests.length; i++) {
            boolean result = runTest(i + 1, descriptions[i], tests[i]);
            if (result) {
                passedTests++;
            }
        }

        System.out.printf("\nРезультат: %d/%d тестов пройдено\n", passedTests, tests.length);
    }
    /**
     * Выполняет один void-тест.
     * Тест считается пройденным, если не было выброшено исключение.
     *
     * @param testNumber номер теста (для отображения)
     * @param description описание теста
     * @param test функциональный интерфейс с void-тестом для выполнения
     * @return true если тест выполнен без исключений, false в противном случае
     * @throws IllegalArgumentException если testNumber меньше 1 или description/test равны null
     */
    public static boolean runVoidTest(int testNumber, String description, VoidTestMethod test) {
        if (testNumber < 1) {
            throw new IllegalArgumentException("Номер теста должен быть положительным: " + testNumber);
        }
        if (description == null || test == null) {
            throw new IllegalArgumentException("Описание и тест не могут быть null");
        }
        System.out.printf("\nТест %d: %s\n", testNumber, description);
        try {
            test.run();
            System.out.println("Пройден (void метод выполнен)");
            return true;
        } catch (Exception e) {
            System.out.println("Не пройден: " + e.getMessage());
            return false;
        }
    }
    /**
     * Выполняет набор void-тестов.
     *
     * @param taskName название задачи/набора тестов
     * @param descriptions массив описаний тестов
     * @param tests массив void-тестов для выполнения
     * @throws IllegalArgumentException если arrays не совпадают по длине или содержат null
     */
    public static void runVoidTestSuite(String taskName, String[] descriptions, VoidTestMethod[] tests) {
        if (taskName == null) {
            throw new IllegalArgumentException("Название задачи не может быть null");
        }
        System.out.println("\n" + "=".repeat(50));
        System.out.println(taskName);
        System.out.println("=".repeat(50));

        if (descriptions.length != tests.length) {
            throw new IllegalArgumentException("Ошибка: количество описаний не совпадает с количеством тестов");
        }

        int passedTests = 0;

        for (int i = 0; i < tests.length; i++) {
            boolean result = runVoidTest(i + 1, descriptions[i], tests[i]);
            if (result) {
                passedTests++;
            }
        }

        System.out.printf("\nРезультат: %d/%d тестов пройдено\n", passedTests, tests.length);
    }
}
