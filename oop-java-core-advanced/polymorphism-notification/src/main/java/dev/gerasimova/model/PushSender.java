package dev.gerasimova.model;
/**
 * Класс для отправки сообщений через пуш-уведомления. Имплементирует интерфейс NotificationSender.
 *
 * @see NotificationSender
 * @see EmailSender
 * @see SmsSender
 */
public class PushSender implements NotificationSender {
    /**
     * Метод позволяет отправлять сообщения через пуш-уведомления.
     * @param message - сообщение для отправки.
     */
    @Override
    public void send(String message) {
        System.out.println(STR."\{message}: Сообщение отправлено через пуш-уведомление!");
    }
}
