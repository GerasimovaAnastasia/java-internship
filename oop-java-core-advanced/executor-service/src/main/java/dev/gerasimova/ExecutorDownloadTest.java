package dev.gerasimova;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Тестовый класс для демонстрации работы с ExecutorService.
 * Показывает использование пула потоков для выполнения задач.
 */

public class ExecutorDownloadTest {

    /**
     * Приватный конструктор.
     */
    private ExecutorDownloadTest() {
        throw new UnsupportedOperationException("ExecutorDownloadTest class");
    }

    /**
     * Запускает все тесты для задачи по многопоточности с ExecutorService.
     *
     * @see #testExecutorService()
     */
    public static void runExecutorDownloadTests() {
        String[] descriptions = {
                "Запускаем 5 задач в пуле из 3 потоков"
        };

        VoidTestMethod[] tests = {
                ExecutorDownloadTest::testExecutorService
        };

        TestRunnerUtil.runVoidTestSuite("ЗАДАЧА 7: Современная Многопоточность (ExecutorService)", descriptions, tests);
    }

    /**
     * Тестирует выполнение задач в пуле потоков ExecutorService.
     * Демонстрирует ограничение количества одновременно работающих потоков.
     */
    public static void testExecutorService() {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        long startTime;
        try {
            List<Downloader> downloaders = createDownloaders();
            startTime = System.currentTimeMillis();

            System.out.println("Ожидаемое поведение: одновременно работают не более 3 потоков\n");

            for (Downloader downloader : downloaders) {
                executor.submit(downloader);
            }

            System.out.println("Все задачи отправлены в ExecutorService");

        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.out.println("Принудительное завершение...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nОбщее время выполнения: " + (endTime - startTime) + " мс");
    }
    /**
     * Создает список задач для скачивания файлов.
     *
     * @return список задач Downloader
     */
    public static List<Downloader> createDownloaders() {
        List<Downloader> downloaders = new ArrayList<>();
        downloaders.add(new Downloader("file1.txt"));
        downloaders.add(new Downloader("file2.jpg"));
        downloaders.add(new Downloader("file3.pdf"));
        downloaders.add(new Downloader("file4.zip"));
        downloaders.add(new Downloader("file5.mp4"));
        return downloaders;
    }
}
