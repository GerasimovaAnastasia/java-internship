package dev.gerasimova.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * DTO параметров пагинации для сохранения данных из запроса.
 * Содержит информацию о настройке пагинации: номер начальной страницы,
 * количество записей на странице, тип сортировки.
 */
public record PaginationParam(
        @Schema(description = "Номер начальной страницы", example = "0")
        @Min(0) int page,
        @Schema(description = "Количество ответов на странице", example = "5")
        @Min(1) @Max(100) int size,
        @Schema(description = "Параметры сортировки",
                example = "title,asc")
        String sort
) {}
