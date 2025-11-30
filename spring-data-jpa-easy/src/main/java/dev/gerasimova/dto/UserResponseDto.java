package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * DTO аккаунта пользователя для демонстрации данных пользователю.
 * Содержит информацию о пользователе: логин.
 */
@Schema(description = "DTO для вывода информации о пользователе")
@Builder
public record UserResponseDto(@Schema(description = "Логин пользователя", example = "user1")
                              String username) {
}
