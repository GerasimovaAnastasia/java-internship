package dev.gerasimova.model;
/**
 * Класс для отправки сообщений через смс. Имплементирует интерфейс NotificationSender.
 *
 * @see NotificationSender
 * @see PushSender
 * @see EmailSender
 */
public class SmsSender implements NotificationSender {
    /**
     * Метод позволяет отправлять сообщения через смс.
     * @param message - сообщение для отправки.
     */
    @Override
    public void send(String message) {
        System.out.println(STR."\{message}: Сообщение отправлено через смс!");
    }
}
