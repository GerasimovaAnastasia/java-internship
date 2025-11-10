package dev.gerasimova.introduction_spring_framework.service;

import org.springframework.stereotype.Service;

/**
 * Сервис, реализующий бизнес-логику отправки сообщения по email.
 * Имплементирует интерфейс NotificationService.
 *
 * @see NotificationService
 * @see SmsNotificationService
 */
@Service
public class EmailNotificationService implements NotificationService {

    /**
     * Метод возвращает сообщение об отправки сообщения по почте.
     * @return - сообщение.
     */
    @Override
    public String sendNotification() {
        return "Отправка сообщения по электронной почте";
    }
}
