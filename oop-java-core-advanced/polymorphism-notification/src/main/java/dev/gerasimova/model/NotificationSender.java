package dev.gerasimova.model;
/**
 * Интерфейс для отправки сообщений.
 *
 * @see EmailSender
 * @see PushSender
 * @see SmsSender
 */
public interface NotificationSender {
    /**
     * Метод для отправки сообщений разными способами.
     * @param message - сообщение для отправки.
     */
    void send(String message);
}
