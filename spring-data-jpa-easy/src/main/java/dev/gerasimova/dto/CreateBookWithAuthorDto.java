package dev.gerasimova.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO книги с автором для сохранения данных из запроса.
 * Содержит информацию о книге и авторе: название, имя автора, фамилию автора, цену, год выпуска.
 */
@Schema(description = "DTO книги с автором")
public record CreateBookWithAuthorDto(@Schema(description = "Название книги", example = "Война и мир")
                                      @NotBlank(message = "Название обязательно")
                                      String title,
                                      @Schema(description = "Имя автора книги", example = "Лев")
                                      @NotBlank(message = "Имя автора обязательно")
                                      String authorName,
                                      @Schema(description = "Фамилия автора книги", example = "Толстой")
                                      @NotBlank(message = "Фамилия автора обязательна")
                                      String authorSurname,
                                      @Schema(description = "Цена книги в рублях", example = "400.0")
                                      @NotNull(message = "Цена обязательна")
                                      @PositiveOrZero(message = "Цена не может быть отрицательной")
                                      Double price,
                                      @Schema(description = "Год выпуска книги", example = "2013")
                                      @NotNull(message = "Год обязателен")
                                      @PositiveOrZero(message = "Год не может быть отрицательным")
                                      Integer yearRelease) {
}