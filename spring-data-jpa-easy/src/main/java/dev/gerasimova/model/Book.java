package dev.gerasimova.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Модель книги в системе.
 * Содержит информацию о книге: идентификатор, название, автора и цену, год выпуска.
 */

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Модель книги")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Название книги", example = "Превращение")
    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Schema(description = "Цена книги в рублях", example = "400.0")
    @Column(nullable = false)
    private Double price;

    @Schema(description = "Год выхода книги", example = "2023")
    @Column(nullable = false, name = "year_release")
    private Integer yearRelease;

    /**
     * Создает объект книги с указанными параметрами.
     *
     * @param title название книги (не может быть пустым)
     * @param author автор книги (не может быть пустым)
     * @param price цена книги (не может быть отрицательной)
     * @param date год выпуска книги (не может быть меньше минимально допустимого значения)
     * @throws IllegalArgumentException если параметры не соответствуют ограничениям
     */
    public Book(String title, Author author, Double price, Integer date) {
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