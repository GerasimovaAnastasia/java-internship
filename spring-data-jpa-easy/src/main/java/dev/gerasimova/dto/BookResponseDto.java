package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * DTO книги для демонстрации данных пользователю.
 * Содержит информацию о книге: название, фамилию автора, цену, год выпуска.
 */
@Schema(description = "DTO для вывода информации о книге")
@Builder
public record BookResponseDto(@Schema(description = "Название книги", example = "Война и мир")
                           String title,
                           @Schema(description = "Фамилия автора книги", example = "Толстой")
                           String authorSurname,
                           @Schema(description = "Цена книги в рублях", example = "1400.0")
                           Double price,
                           @Schema(description = "Год выпуска книги", example = "2013")
                           Integer yearRelease) {
}