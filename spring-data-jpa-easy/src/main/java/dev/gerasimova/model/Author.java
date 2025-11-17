package dev.gerasimova.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель автора в системе.
 * Содержит информацию об авторе: идентификатор, имя, фамилию, список книг автора.
 */
@Entity
@Table(name = "authors")
@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Модель автора")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Schema(description = "Имя автора", example = "Лев")
    private String name;
    @Schema(description = "Фамилия автора", example = "Толстой")
    @Column(nullable = false)
    private String surname;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    /**
     * Создает объект книги с указанными параметрами.
     *
     * @param name имя автора (не может быть пустым)
     * @param surname фамилия автора (не может быть пустым)
     */
    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.books = new ArrayList<>();
    }

    /**
     * Возвращает строковое представление автора.
     *
     * @return строку в формате "Author{id=1, name='Федор',
     * surname='Достоевский' }"
     */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
