package dev.gerasimova;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Тестовый класс для тестов для задачи асинхронной обработки ошибок с exceptionally().
 *
 * @see CompletableFuture
 * @see TestRunnerUtil
 */
public class CompletableFutureExceptionHandlingTest {

    /**
     * Приватный конструктор.
     */
    private CompletableFutureExceptionHandlingTest() {
        throw new UnsupportedOperationException("CompletableFutureExceptionHandlingTest class");
    }


    /**
     * Запускает все тесты для асинхронной обработки ошибок с exceptionally().
     *
     * @see #testExceptionHandling()
     */
    public static void runExceptionHandlingTests() {
        String[] descriptions = {
                "Тестируем обработку ошибок в асинхронной цепочке"
        };

        VoidTestMethod[] tests = {
                CompletableFutureExceptionHandlingTest::testExceptionHandling
        };

        TestRunnerUtil.runVoidTestSuite("ЗАДАЧА 9: Асинхронная Обработка Ошибок", descriptions, tests);
    }

    /**
     * Тестирует обработку исключений в асинхронной цепочке.
     * Демонстрирует перехват ошибок с помощью exceptionally().
     *
     * @see CompletableFuture#supplyAsync(Supplier)
     * @see CompletableFuture#thenApply(Function)
     * @see CompletableFuture#thenAccept(Consumer)
     * @see CompletableFuture#exceptionally(Function)
     */
    public static void testExceptionHandling() {
        System.out.println("Task submitted...");

        CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " - Начали запрос к API");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return "Прервано";
                    }

                    if (true) {                                     //для теста
                        throw new RuntimeException("API недоступно!");
                    }

                    String apiResponse = "{\"data\": \"Hello from API\", \"status\": 200}";
                    System.out.println(Thread.currentThread().getName() + " - Получили ответ от API");
                    return apiResponse;
                })
                .thenApply(apiResponse -> {
                    System.out.println(Thread.currentThread().getName() + " - Парсим JSON...");
                    String parsedData = apiResponse.replace("{\"data\": \"", "")
                            .replace("\", \"status\": 200}", "");
                    return "Данные: " + parsedData;
                })
                .exceptionally(throwable -> {
                    System.out.println(Thread.currentThread().getName() + " - Перехватили ошибку");
                    System.err.println("Ошибка: " + throwable.getMessage());

                    return "Дефолтные данные";
                })
                .thenAccept(processedData -> {
                    System.out.println(Thread.currentThread().getName() + " - Выводим результат:");
                    System.out.println(processedData);
                });

        System.out.println("Асинхронная задача запущена, main thread свободен!\n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
