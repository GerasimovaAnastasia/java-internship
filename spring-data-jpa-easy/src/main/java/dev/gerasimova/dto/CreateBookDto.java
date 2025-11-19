package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO книги для сохранения данных из запроса.
 * Содержит информацию о книге: название, id автора, цену, год выпуска.
 */
@Schema(description = "DTO книги")
public record CreateBookDto(@Schema(description = "Название книги", example = "Война и мир")
                            @NotBlank(message = "Название обязательно")
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
                            @PositiveOrZero(message = "Год не может быть отрицательным")
                            Integer yearRelease) {
}