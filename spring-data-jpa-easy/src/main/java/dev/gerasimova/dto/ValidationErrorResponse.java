package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Модель ошибок валидации API")
public record ValidationErrorResponse(
        List<FieldError> errors
) {
    @Schema(description = "Модель ошибки валидации API")
    public record FieldError(
            @Schema(description = "Поле с ошибкой", example = "title")
            String field,
            @Schema(description = "Сообщение об ошибке", example = "Сообщение об ошибке")
            String message
    ) {}
}
