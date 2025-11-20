package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * DTO товара для демонстрации данных пользователю.
 * Содержит информацию о товаре: название, название категории, цену.
 */
@Schema(description = "DTO для вывода информации о товаре")
@Builder
public record ProductResponseDto(@Schema(description = "Название товара", example = "Стул")
                           String title,
                                 @Schema(description = "Название категории", example = "Мебель")
                           String nameCategory,
                                 @Schema(description = "Цена книги в рублях", example = "1400.0")
                           Double price) {
}