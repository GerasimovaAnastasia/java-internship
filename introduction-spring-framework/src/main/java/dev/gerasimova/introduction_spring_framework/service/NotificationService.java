package dev.gerasimova.introduction_spring_framework.service;

/**
 * Интерфейс для отправки сообщений.
 */
public interface NotificationService {

    /**
     * Метод для отправки сообщений разными способами.
     * @return - сообщение о выборе типа отправке.
     */
    String sendNotification();
}
