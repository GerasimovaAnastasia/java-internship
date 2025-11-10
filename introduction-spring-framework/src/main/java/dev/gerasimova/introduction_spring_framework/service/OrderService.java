package dev.gerasimova.introduction_spring_framework.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для логики внедрения типа отправки сообщения.
 *
 * @see Qualifier
 */
@Service
public class OrderService {
    private final NotificationService notificationService;

    /**
     * Конструктор внедряет тип отправки с помощью аннотации (@Qualifier("emailNotificationService")).
     * Решает проблему когда есть несколько бинов, реализующих один интерфейс.
     *
     *  @param notificationService - тип отправки.
     */
    public OrderService(@Qualifier("emailNotificationService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Метод выводит информацию о способе отправки.
     * @return - информация.
     */
    public String processOrder() {
        return "Выбор способа отправки: " + notificationService.sendNotification();
    }
}
