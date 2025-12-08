package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * DTO для отправки уведомления в службу уведомлений NotificationService.
 */
@Schema(description = "DTO для отправки уведомления в службу уведомлений")
@Builder
public record BookNotificationRequest(
        @NotBlank(message = "Логин должен быть обязательно")
        String username,
        @NotBlank(message = "Сообщение должно быть обязательно")
        String message) {
}
