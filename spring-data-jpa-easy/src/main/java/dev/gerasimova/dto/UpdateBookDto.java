package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
/**
 * DTO книги для сохранения данных из запроса.
 * Содержит информацию о книге: название, id автора, цену, год выпуска.
 * Используется для изменения данных о книге.
 */
@Schema(description = "DTO для обновления данных книги")
public record UpdateBookDto(
        @Schema(description = "Название книги", example = "Война и мир")
        @NotBlank(message = "Название обязательно")
        @Size(min = 3, max = 100, message = "Название должно быть от 3 до 100 символов")
        String title,
        @Schema(description = "ID автора книги", example = "1")
        @NotNull(message = "ID автора обязателен")
        @Positive(message = "ID автора должен быть положительным")
        Long authorID,
        @Schema(description = "Цена книги в рублях", example = "400.0")
        @NotNull(message = "Цена обязательна")
        @PositiveOrZero(message = "Цена не может быть отрицательной")
        Double price,
        @Schema(description = "Год выпуска книги", example = "2013")
        @NotNull(message = "Год обязателен")
        @Min(value = 1900, message = "Минимальный год 1900")
        Integer yearRelease
) {
}
