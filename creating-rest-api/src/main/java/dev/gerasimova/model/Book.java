package dev.gerasimova.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Модель книги в библиотечной системе.
 * Содержит информацию о книге: идентификатор, название, автора и цену.
 */
@Getter
@Setter
@Schema(description = "Модель книги")
public class Book {
    @Schema(description = "Уникальный идентификатор книги", example = "1")
    @NotNull @Positive
    private Long id;

    @Schema(description = "Название книги", example = "Transformation")
    @NotBlank
    private String title;

    @Schema(description = "Автор книги", example = "Kafka")
    @NotBlank
    private String author;

    @Schema(description = "Цена книги в рублях", example = "400.0")
    @NotNull @PositiveOrZero
    private Double price;

    /**
     * Конструктор по умолчанию.
     * Требуется для корректной работы Spring и Jackson.
     */
    public Book() {
    }

    /**
     * Создает объект книги с указанными параметрами.
     *
     * @param id уникальный идентификатор книги (должен быть положительным)
     * @param title название книги (не может быть пустым)
     * @param author автор книги (не может быть пустым)
     * @param price цена книги (не может быть отрицательной)
     * @throws IllegalArgumentException если параметры не соответствуют ограничениям
     */
    public Book(Long id, String title, String author, Double price) {
        this.id = Objects.requireNonNull(id, "ID не может быть null");
        this.title = Objects.requireNonNull(title, "Название не может быть null");
        this.author = Objects.requireNonNull(author, "Автор не может быть null");
        this.price = Objects.requireNonNull(price, "Цена не может быть null");

        if (id <= 0) throw new IllegalArgumentException("ID должен быть положительным");
        if (title.isBlank()) throw new IllegalArgumentException("Название не может быть пустым");
        if (author.isBlank()) throw new IllegalArgumentException("Автор не может быть пустым");
        if (price < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
    }

    /**
     * Возвращает строковое представление книги.
     *
     * @return строку в формате "Book{id=1, author='Kafka',
     * title='Transformation', price=400.0}"
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}