package dev.gerasimova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Дто для создания уведомления
 * @param username - логин пользователя
 * @param message - сообщение пользователя
 */
public record NotificationRequest(
        @NotBlank(message = "username не может быть пустым")
        String username,
        @NotBlank(message = "message не может быть пустым")
        @Size(min = 3, max = 50, message = "Сообщение должно быть от 3-50 символов")
        String message
) {
}
