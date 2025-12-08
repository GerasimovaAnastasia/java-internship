package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Дто для ответа пользователю на запрос о создании уведомления.
 *
 * @param status - статус сообщения
 * @param message - сообщение пользователя
 * @param notificationId - Id сообщения
 */
@Schema(description = "Ответ на запрос создания уведомления")
public record NotificationResponse(
        @Schema(
                description = "Статус обработки уведомления",
                example = "SUCCESS",
                allowableValues = {"SUCCESS", "ERROR", "PENDING"}
        )
        String status,
        @Schema(
                description = "Сообщение для пользователя",
                example = "Уведомление поставлено в очередь"
        )
        String message,
        @Schema(
                description = "Уникальный идентификатор уведомления",
                example = "1"
        )
        String notificationId) {
}
