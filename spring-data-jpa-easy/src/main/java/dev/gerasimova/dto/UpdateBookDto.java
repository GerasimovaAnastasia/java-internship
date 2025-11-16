package dev.gerasimova.dto;

import dev.gerasimova.model.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO книги для сохранения данных из запроса.
 * Содержит информацию о книге: название, автора, цену, год выпуска.
 * Используется для изменения данных о книге.
 */
public record UpdateBookDto(
        @Schema(description = "Название книги", example = "Transformation")
        @NotBlank(message = "Название обязательно")
        String title,
        @Schema(description = "Автор книги", example = "Kafka")
        @NotBlank(message = "Автор обязателен")
        String author,
        @Schema(description = "Цена книги в рублях", example = "400.0")
        @NotNull(message = "Цена обязательна")
        @PositiveOrZero(message = "Цена не может быть отрицательной")
        Double price,
        @Schema(description = "Год выпуска книги", example = "2013")
        @NotNull(message = "Год обязателен")
        @PositiveOrZero(message = "Год не может быть отрицательным")
        Integer yearRelease
) {
    /**
     * Метод для обновления существующий книги
     * @param book - книга, у которой изменились поля.
     */
    public void updateBook(Book book) {
        book.setTitle(title());
        book.setAuthor(author());
        book.setPrice(price());
        book.setYearRelease(yearRelease());
    }
}
