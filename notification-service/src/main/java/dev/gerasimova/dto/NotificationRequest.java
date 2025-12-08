package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Дто для создания уведомления
 * @param username - логин пользователя
 * @param message - сообщение пользователя
 */
@Schema(description = "Запрос на создание уведомления")
public record NotificationRequest(
        @Schema(
                description = "Логин пользователя",
                example = "admin1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "username не может быть пустым")
        String username,
        @Schema(
                description = "Текст уведомления",
                example = "Добавлена новая книга",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 3,
                maxLength = 50
        )
        @NotBlank(message = "message не может быть пустым")
        @Size(min = 3, max = 50, message = "Сообщение должно быть от 3-50 символов")
        String message
) {
}
