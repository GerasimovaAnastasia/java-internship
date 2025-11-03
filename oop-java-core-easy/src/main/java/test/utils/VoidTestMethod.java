package test.utils;
/**
 * Функциональный интерфейс для тестовых методов, не возвращающих результат (void).
 * Используется для тестов, которые выполняют действия без возврата значения.
 *
 * @see TestRunnerUtil
 * @see TestMethod
 * @functionalInterface
 */
@FunctionalInterface
public interface VoidTestMethod {
    /**
     * Выполняет тест без возврата результата.
     * Тест считается пройденным, если не было выброшено исключение.
     */
    void run();
}
