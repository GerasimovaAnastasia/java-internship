package dev.gerasimova.dto;

import dev.gerasimova.model.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * DTO аккаунта пользователя для его регистрации в системе.
 * Содержит информацию о пользователе: логин, пароль, роль.
 */
public record CreateUserWithRoleDto(
        @Schema(description = "Логин пользователя", example = "user1")
        @NotBlank(message = "Логин обязательно должен быть")
        String username,
        @Schema(description = "Пароль пользователя", example = "password")
        @NotBlank(message = "Пароль обязательно должен быть")
        String password,
        @Schema(description = "Роль пользователя/Роль администратора", example = "ROLE_USER")
        @NotNull(message = "Роль пользователя обязательна")
        UserRole role
) {}
