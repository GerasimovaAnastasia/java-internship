package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.io.Serializable;
/**
 * DTO отзывов для сохранения данных из запроса.
 * Содержит информацию об отзывах: идентификатор товара, рейтинг, комментарий и автора.
 */
@Schema(description = "DTO отзыва")
public record CreateReviewDto(
        @Schema(description = "Id продукта", example = "1")
        @NotNull(message = "Id продукта обязательно")
        Long productId,
        @Schema(description = "Рейтинг продукта", example = "1-5")
        @Min(1) @Max(5)
        int rating,
        @Schema(description = "Комментарий", example = "Отличный товар!")
        @Size(max = 1000)
        String comment,
        @Schema(description = "Автор отзыва", example = "Виктор")
        @NotBlank(message = "Автор обязателен")
        String author
) implements Serializable {}
