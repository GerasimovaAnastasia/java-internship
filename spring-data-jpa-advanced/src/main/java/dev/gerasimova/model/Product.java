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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель товара в системе.
 * Содержит информацию о продукте: идентификатор, название, категорию и цену.
 */

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Модель товара")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Название товара", example = "Стул")
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Schema(description = "Цена товара в рублях", example = "400.0")
    @Column(nullable = false)
    private Double price;

    /**
     * Создает объект продукта с указанными параметрами.
     *
     * @param name     название продукта (не может быть пустым)
     * @param category категории товара (не может быть пустым)
     * @param price    цена товара (не может быть отрицательной)
     */
    @Builder
    public Product(String name, Category category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * Возвращает строковое представление продукта.
     *
     * @return строку в формате "Product{id=1, name='Стул',
     * category='Мебель', price=400.0 }"
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category.getName() +
                ", price=" + price +
                '}';
    }
}
