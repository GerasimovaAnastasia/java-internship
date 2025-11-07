package dev.gerasimova;
/**
 * Класс, реализующий задачу скачивания файла.
 * Имитирует процесс скачивания с помощью задержки потока.
 * Реализует интерфейс Runnable для выполнения в отдельном потоке.
 */
public class Downloader implements Runnable {
    private final String fileName;

    /**
     * Создает задачу скачивания для указанного файла.
     *
     * @param fileName имя файла для скачивания
     */
    public Downloader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Выполняет процесс скачивания файла.
     * Имитирует скачивание с помощью 2-секундной задержки.
     * Выводит информацию о начале и завершении скачивания.
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " начал скачивание: " + fileName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Скачивание прервано: " + fileName);
        }
        System.out.println(Thread.currentThread().getName() + " завершил скачивание: " + fileName);
    }
}