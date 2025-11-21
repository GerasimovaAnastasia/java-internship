package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO товара для сохранения данных из запроса.
 * Содержит информацию о товаре: название, id категории, цену
 * Используется для изменения данных о товаре.
 */
@Schema(description = "DTO для обновления данных товара")
public record UpdateProductDto(
        @Schema(description = "Название товара", example = "Стул")
        @NotBlank(message = "Название обязательно")
        String name,
        @Schema(description = "ID категории товара", example = "1")
        @NotNull(message = "ID категории обязателен")
        @Positive(message = "ID категории должен быть положительным")
        Long categoryID,
        @Schema(description = "Цена товара в рублях", example = "400.0")
        @NotNull(message = "Цена обязательна")
        @PositiveOrZero(message = "Цена не может быть отрицательной")
        Double price
) implements Serializable {
}
