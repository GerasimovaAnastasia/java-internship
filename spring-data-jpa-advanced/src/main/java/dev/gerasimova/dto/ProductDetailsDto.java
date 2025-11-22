package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;
@Builder
public record ProductDetailsDto(
        @Schema(description = "Название товара", example = "Стул")
        String name,
        @Schema(description = "Название категории товара", example = "Мебель")
        String categoryName,
        @Schema(description = "Цена товара в рублях", example = "400.0")
        Double price,
        @Schema(description = "Список отзывов товара")
        List<ProductReviewDto> reviewList
) implements Serializable {
}
