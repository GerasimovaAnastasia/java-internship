package dev.gerasimova;

import java.util.ArrayList;
import java.util.List;

/**
 * Тестовый класс для проверки работы потоков.
 * Проверяет работу последовательного и параллельного скачивания.
 */
public class DownloadTest {

    /**
     * Приватный конструктор.
     */
    private DownloadTest() {
        throw new UnsupportedOperationException("DownloadTest class");
    }

    /**
     * Запускает все тесты для задачи основам многопоточности (Thread и Runnable).
     *
     * @see #testSequentialDownload()
     * @see #testParallelDownload()
     */
    public static void runDownloadTests() {
        String[] descriptions = {
                "Запускаем скачивание последовательно",
                "Запускаем скачивание параллельно"
        };

        VoidTestMethod[] tests = {
                DownloadTest::testSequentialDownload,
                DownloadTest::testParallelDownload
        };

        TestRunnerUtil.runVoidTestSuite("ЗАДАЧА 6: Основы Многопоточности (Thread и Runnable)", descriptions, tests);
    }

    /**
     *  Тест имитирует последовательное скачивание файлов и засекает время.
     *  Все запуски происходят в одном потоке.
     */
    public static void testSequentialDownload() {

        List<Downloader> downloaders = createDownloaders();
        long startTime = System.currentTimeMillis();

        for (Downloader downloader : downloaders) {
            downloader.run();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Последовательное скачивание заняло: " + duration + " мс");
        System.out.println("Ожидалось: ~10000 мс (5 файлов * 2000 мс)\n");
    }

    /**
     * Тест имитирует параллельное скачивание файлов и засекает время.
     * Все запуски происходят в отдельных потоках.
     */
    public static void testParallelDownload() {

        List<Downloader> downloaders = createDownloaders();
        List<Thread> threads = new ArrayList<>();
        long startTime = System.currentTimeMillis();


        for (Downloader downloader : downloaders) {
            Thread thread = new Thread(downloader);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ожидание потоков прервано");
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Параллельное скачивание заняло: " + duration + " мс");
        System.out.println("Ожидалось: ~2000 мс (все файлы одновременно)\n");
    }

    /**
     * Создает список загрузчиков с тестовыми данными.
     *
     * @return список загрузчиков для тестирования многопоточности.
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
