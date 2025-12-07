package dev.gerasimova.dto;
/**
 * Дто для ответа пользователю на запрос о создании уведомления.
 *
 * @param status - статус сообщения
 * @param message - сообщение пользователя
 * @param notificationId - Id сообщения
 */
public record NotificationResponse(
        String status,
        String message,
        String notificationId) {
}
