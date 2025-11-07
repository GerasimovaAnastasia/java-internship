package dev.gerasimova;

/**
 * Класс для имитации скачивания файлов.
 */
public class Downloader implements Runnable {
    private final String fileName;

    /**
     * Создает объект загрузчика с указанными параметрами.
     * @param fileName - название файла для скачивания.
     */
    public Downloader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Метод имитирует скачивание файла для задачи многопоточности.
     * Поток спит - время скачивания файла.
     * Метод позволяет отследить время скачивания файла.
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