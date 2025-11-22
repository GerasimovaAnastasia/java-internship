package dev.gerasimova.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Модель отзывов в системе.
 * Содержит информацию об отзывах: идентификатор, идентификатор товара, рейтинг, комментарий и автора.
 */
@Document(collection = "reviews")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Модель отзывов")
public class ProductReview {
    @Id
    private String id;

    @Field("product_id")
    private Long productId;

    @Field("rating")
    private int rating;

    @Field("comment")
    private String comment;

    @Field("author")
    private String author;
    /**
     * Создает объект отзыва с указанными параметрами.
     *
     * @param productId идентификатор продукта (не может быть null)
     * @param rating рейтинг товара (не может быть null)
     * @param comment комментарий (не может быть пустым)
     * @param author автор отзыва (не может быть пустым)
     */
    @Builder
    public ProductReview(Long productId, int rating, String comment, String author) {
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.author = author;
    }
}
