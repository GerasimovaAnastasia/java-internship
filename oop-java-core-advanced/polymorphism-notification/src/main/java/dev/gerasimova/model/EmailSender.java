package dev.gerasimova.model;

/**
 * Класс для отправки сообщений через почту. Имплементирует интерфейс NotificationSender.
 *
 * @see NotificationSender
 * @see PushSender
 * @see SmsSender
 */
public class EmailSender implements NotificationSender {

    /**
     * Метод позволяет отправлять сообщения через почту.
     * @param message - сообщение для отправки.
     */
    @Override
    public void send(String message) {
        System.out.println(STR."\{message}: Сообщение отправлено через почту!");
    }
}
