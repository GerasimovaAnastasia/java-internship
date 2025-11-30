package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
/**
 * DTO аккаунта пользователя для его регистрации в системе.
 * Содержит информацию о пользователе: логин, пароль.
 */
@Schema(description = "DTO аккаунта пользователя")
public record CreateUserDto(@Schema(description = "Логин пользователя", example = "user1")
                            @NotBlank(message = "Логин обязательно должен быть")
                            String username,
                            @Schema(description = "Пароль пользователя", example = "password")
                            @NotBlank(message = "Пароль обязательно должен быть")
                            String password) {
}
