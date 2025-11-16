package dev.gerasimova.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


/**
 * Модель книги в библиотечной системе.
 * Содержит информацию о книге: идентификатор, название, автора и цену, год выпуска.
 */

@Entity
@Table(name = "books")
@Getter
@Setter
@Schema(description = "Модель книги")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Название книги", example = "Transformation")
    @Column(nullable = false)
    private String title;

    @Schema(description = "Автор книги", example = "Kafka")
    @Column(nullable = false)
    private String author;

    @Schema(description = "Цена книги в рублях", example = "400.0")
    @Column(nullable = false)
    private Double price;

    @Schema(description = "Год выхода книги", example = "2023")
    @Column(nullable = false, name = "year_release")
    private Integer yearRelease;

    /**
     * Конструктор по умолчанию.
     * Требуется для корректной работы Spring и Jackson.
     */
    public Book() {
    }

    /**
     * Создает объект книги с указанными параметрами.
     *
     * @param title название книги (не может быть пустым)
     * @param author автор книги (не может быть пустым)
     * @param price цена книги (не может быть отрицательной)
     * @param date год выпуска книги (не может быть меньше минимально допустимого значения)
     * @throws IllegalArgumentException если параметры не соответствуют ограничениям
     */
    public Book(String title, String author, Double price, Integer date) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.yearRelease = date;
    }

    /**
     * Возвращает строковое представление книги.
     *
     * @return строку в формате "Book{id=1, author='Kafka',
     * title='Transformation', price=400.0,yearRelease=2023 }"
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", yearRelease=" + yearRelease +
                '}';
    }
}