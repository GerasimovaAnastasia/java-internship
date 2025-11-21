package dev.gerasimova.service;

import dev.gerasimova.dto.CreateReviewDto;
import dev.gerasimova.dto.ProductReviewDto;
import dev.gerasimova.model.ProductReview;
import org.springframework.stereotype.Component;
import dev.gerasimova.model.Product;
import dev.gerasimova.model.Category;
import dev.gerasimova.dto.ProductResponseDto;
import dev.gerasimova.dto.CreateProductDto;
/**
 * Компонент для преобразования между сущностями и DTO.
 * Обеспечивает маппинг данных между сервисным слоем и API.
 *
 * @see Product
 * @see Category
 * @see ProductResponseDto
 * @see CreateProductDto
 */
@Component
public class EntityMapper {
    /**
     * Преобразует сущность продукта в DTO для ответа.
     * Включает основные данные продукта и название категории.
     *
     * @param product сущность продукта для преобразования
     * @return ProductResponseDto с данными продукта для ответа клиенту
     * @throws NullPointerException если product или category равен null
     */
    public ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .nameCategory(product.getCategory().getName())
                .title(product.getName())
                .price(product.getPrice())
                .build();
    }
    /**
     * Преобразует DTO создания продукта в сущность продукта.
     * Не включает категорию - она должна быть установлена отдельно.
     *
     * @param dto DTO с данными для создания продукта
     * @return сущность продукта без установленной категории
     * @see Product#setCategory(Category)
     */
    public Product toEntity(CreateProductDto dto) {
        return Product.builder()
                .name(dto.name())
                .price(dto.price())
                .build();
    }
    /**
     * Преобразует DTO создания отзыва в сущность отзыва.
     *
     * @param dto DTO с данными для создания отзыва
     * @return сущность отзыва
     */
    public ProductReview toEntity(CreateReviewDto dto) {
        return ProductReview.builder()
                .productId(dto.productId())
                .rating(dto.rating())
                .comment(dto.comment())
                .author(dto.author())
                .build();
    }
    /**
     * Преобразует сущность отзыва в DTO для ответа.
     * Включает основные данные отзыва и название продукта.
     *
     * @param productName название продукта, на который делают отзыв
     * @param productReview сущность отзыва
     * @return ProductReviewDto с данными отзыва для ответа клиенту
     */
    public ProductReviewDto toDto(ProductReview productReview, String productName) {
        return ProductReviewDto.builder()
                .productId(productReview.getProductId())
                .productName(productName)
                .rating(productReview.getRating())
                .comment(productReview.getComment())
                .author(productReview.getAuthor())
                .build();
    }
}
