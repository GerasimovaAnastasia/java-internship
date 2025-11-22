package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Дто для ошибок
 * @param message
 * @param timestamp
 */
@Schema(description = "Модель ошибки API")
public record ErrorResponse(
        @Schema(description = "Сообщение об ошибке", example = "Сообщение об ошибке")
        String message,

        @Schema(description = "Время возникновения ошибки", example = "2024-01-15T10:30:00")
        LocalDateTime timestamp
) implements Serializable {
    public ErrorResponse(String message) {
        this(message, LocalDateTime.now());
    }
}
