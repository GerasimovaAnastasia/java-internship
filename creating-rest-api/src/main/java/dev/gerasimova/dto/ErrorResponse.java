package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Модель ошибки API")
public record ErrorResponse(
        @Schema(description = "Сообщение об ошибке", example = "Книга с ID 999 не найдена")
        String message,

        @Schema(description = "Время возникновения ошибки", example = "2024-01-15T10:30:00")
        LocalDateTime timestamp
) {
    public ErrorResponse(String message) {
        this(message, LocalDateTime.now());
    }
}
