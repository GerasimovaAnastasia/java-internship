package dev.gerasimova;

/**
 * Функциональный интерфейс для тестовых методов, возвращающих boolean результат.
 * Используется для создания лямбда-выражений и method references в тестовом фреймворке.
 *
 * @see TestRunnerUtil
 * @see VoidTestMethod
 * @functionalInterface
 */
@FunctionalInterface
public interface TestMethod {
    /**
     * Выполняет тест и возвращает результат.
     *
     * @return true если тест пройден, false в противном случае
     */
    boolean run();
}
