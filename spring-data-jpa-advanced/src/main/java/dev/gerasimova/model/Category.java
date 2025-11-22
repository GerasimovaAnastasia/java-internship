package dev.gerasimova.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * Модель категории в системе.
 * Содержит информацию о категории: идентификатор, название.
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Модель категории")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Название категории", example = "Мебель")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    /**
     * Создает объект категории с указанными параметрами.
     *
     * @param name название категории (не может быть пустым)
     */
    @Builder
    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    /**
     * Возвращает строковое представление автора.
     *
     * @return строку в формате "Category{id=1, name='Мебель'}"
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
