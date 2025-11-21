package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
/**
 * DTO отзыва для демонстрации данных пользователю.
 * Содержит информацию об отзывах: идентификатор товара, название товара, рейтинг, комментарий и автора.
 */
@Schema(description = "DTO для демонстрации отзыва")
@Builder
public record ProductReviewDto(@Schema(description = "Id продукта", example = "1")
                               Long productId,
                               @Schema(description = "Название товара", example = "Стул")
                               String productName,
                               @Schema(description = "Рейтинг продукта", example = "1-5")
                               int rating,
                               @Schema(description = "Комментарий", example = "Отличный товар!")
                               String comment,
                               @Schema(description = "Автор отзыва", example = "Виктор")
                               String author) implements Serializable {
}
