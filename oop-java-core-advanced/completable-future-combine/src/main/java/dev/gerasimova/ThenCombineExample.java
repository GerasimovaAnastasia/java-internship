package dev.gerasimova;

import java.util.List;
import java.util.concurrent.CompletableFuture;
/**
 * Класс для демонстрации композиции асинхронных задач с использованием CompletableFuture.thenCombine().
 * Показывает параллельное выполнение независимых задач и объединение их результатов.
 *
 * @see CompletableFuture
 * @see UserProfile
 * @see UserOrders
 * @see DashboardDTO
 */
public class ThenCombineExample {

    /**
     * Приватный конструктор.
     */
    private ThenCombineExample() {
        throw new UnsupportedOperationException("ThenCombineExample class");
    }


    /**
     * Запускает все тесты для задачи по композиции асинхронных задач.
     *
     * @see #testThenCombine()
     */
    public static void runThenCombineTests() {
        String[] descriptions = {
                "Тестируем параллельное выполнение и объединение задач"
        };

        VoidTestMethod[] tests = {
                ThenCombineExample::testThenCombine
        };

        TestRunnerUtil.runVoidTestSuite("ЗАДАЧА 10: Композиция Асинхронных Задач", descriptions, tests);
    }
    /**
     * Тестирует параллельное выполнение двух асинхронных задач и их объединение с помощью thenCombine().
     * Запускает загрузку профиля пользователя (2 секунды) и загрузку заказов пользователя (3 секунды) параллельно.
     * Демонстрирует, что общее время выполнения составляет примерно время самой долгой задачи (3 секунды),
     * а не сумму времен выполнения (5 секунд).
     *
     * @see #loadUserProfileAsync()
     * @see #loadUserOrdersAsync()
     */
    public static void testThenCombine() {
        System.out.println("Запускаем два параллельных запроса...");
        long startTime = System.currentTimeMillis();

        CompletableFuture<UserProfile> futureProfile = loadUserProfileAsync();
        CompletableFuture<UserOrders> futureOrders = loadUserOrdersAsync();

        System.out.println("Оба запроса запущены параллельно в: " + (System.currentTimeMillis() - startTime) + " мс");

        CompletableFuture<DashboardDTO> dashboardFuture = futureProfile.thenCombine(futureOrders,
                (profile, orders) -> {
                    System.out.println("Объединяем результаты в: " + (System.currentTimeMillis() - startTime) + " мс");
                    return new DashboardDTO(profile, orders);
                });

        dashboardFuture.thenAccept(dashboard -> {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("\n Финальный результат:");
            System.out.println(dashboard);
            System.out.println("\n Статистика:");
            System.out.println("Общее время: " + duration + " мс");
            System.out.println("Ожидалось: ~3000 мс (время самого долгого запроса)");
            System.out.println("Без параллелизма было бы: ~5000 мс (2000 + 3000)");


            boolean timeCorrect = duration >= 2900 && duration <= 3500;
            System.out.println("Время выполнения корректно: " + timeCorrect);
        });
        dashboardFuture.join();
    }
    /**
     * Асинхронно загружает профиль пользователя, имитируя запрос к API.
     * Выполняется в отдельном потоке и занимает 2000 миллисекунд.
     *
     * @return CompletableFuture, содержащий объект UserProfile после завершения загрузки
     * @see UserProfile
     */
    private static CompletableFuture<UserProfile> loadUserProfileAsync() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - Начали загрузку профиля...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
            System.out.println(Thread.currentThread().getName() + " - Профиль загружен за 2000 мс");
            return new UserProfile("John Doe", "john@example.com");
        });
    }
    /**
     * Асинхронно загружает заказы пользователя, имитируя запрос к API.
     * Выполняется в отдельном потоке и занимает 3000 миллисекунд.
     * Является самой долгой задачей в цепочке выполнения.
     *
     * @return CompletableFuture, содержащий объект UserOrders после завершения загрузки
     * @see UserOrders
     */
    private static CompletableFuture<UserOrders> loadUserOrdersAsync() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - Начали загрузку заказов...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
            System.out.println(Thread.currentThread().getName() + " - Заказы загружены за 3000 мс");
            return new UserOrders(List.of("Order #001", "Order #002", "Order #003"));
        });
    }
}
