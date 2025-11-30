package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO авторизации для демонстрации токена.
 */
@Schema(description = "DTO для вывода информации о токене")
public record AuthResponse(String token, String type) {
    public AuthResponse(String token) {
        this(token, "Bearer");
    }
}
