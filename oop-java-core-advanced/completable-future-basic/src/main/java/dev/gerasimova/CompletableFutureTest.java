package dev.gerasimova;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Тестовый класс для тестов по асинхронному программированию CompletableFuture.
 *
 * @see CompletableFuture
 * @see TestRunnerUtil
 */
public class CompletableFutureTest {

    /**
     * Приватный конструктор.
     */
    private CompletableFutureTest() {
        throw new UnsupportedOperationException("CompletableFutureTest class");
    }

    /**
     * Запускает все тесты для задачи по асинхронному программированию CompletableFuture.
     *
     * @see #testCompletableFutureChain()
     */
    public static void runCompletableFutureChainTests() {
        String[] descriptions = {
                "Запускаем асинхронную задачу"
        };

        VoidTestMethod[] tests = {
                CompletableFutureTest::testCompletableFutureChain
        };

        TestRunnerUtil.runVoidTestSuite("ЗАДАЧА 8: Введение в Асинхронное Программирование (CompletableFuture)", descriptions, tests);
    }
    /**
     * Тестирует цепочку асинхронных операций CompletableFuture.
     * Демонстрирует неблокирующее выполнение задач.
     *
     * @see CompletableFuture#supplyAsync(Supplier)
     * @see CompletableFuture#thenApply(Function)
     * @see CompletableFuture#thenAccept(Consumer)
     */
    public static void testCompletableFutureChain() {

        System.out.println("Task submitted...");

        CompletableFuture.supplyAsync(() -> {

                    System.out.println(Thread.currentThread().getName() + " - Начали запрос к API");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return "Прервано";
                    }
                    String apiResponse = "{\"data\": \"Hello from API\", \"status\": 200}";
                    System.out.println(Thread.currentThread().getName() + " - Получили ответ от API");
                    return apiResponse;
                })
                .thenApply(apiResponse -> {

                    System.out.println(Thread.currentThread().getName() + " - Парсим JSON...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    String parsedData = apiResponse.replace("{\"data\": \"", "")
                            .replace("\", \"status\": 200}", "");
                    return "Данные: " + parsedData;
                })
                .thenAccept(processedData -> {
                    System.out.println(Thread.currentThread().getName() + " - Выводим результат:");
                    System.out.println(processedData);
                });

        System.out.println("Асинхронная задача запущена, main thread свободен!\n");
    }
}
