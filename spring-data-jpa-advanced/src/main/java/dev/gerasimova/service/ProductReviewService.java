package dev.gerasimova.service;

import dev.gerasimova.dto.CreateReviewDto;
import dev.gerasimova.dto.ProductReviewDto;
import dev.gerasimova.model.Product;
import dev.gerasimova.model.ProductReview;
import dev.gerasimova.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Сервисный класс для управления бизнес-логикой работы с отзывами.
 * Обеспечивает CRUD-операции над сущностью ProductReview через ProductReviewRepository.
 *
 * @see dev.gerasimova.model.ProductReview
 * @see dev.gerasimova.repository.ProductReviewRepository
 */
@Service
@RequiredArgsConstructor
public class ProductReviewService {
    private final ProductReviewRepository reviewRepository;
    private final ProductService productService;
    private final EntityMapper entityMapper;
    /**
     * Сохраняет или обновляет отзыв в хранилище.
     *
     * @param dto отзыва для сохранения
     * @return дто отзыва
     * @see ProductReviewRepository#save(Object)
     */
    public ProductReviewDto addReview(CreateReviewDto dto) {
        Product product = productService.findProductById(dto.productId());
        ProductReview review = reviewRepository.save(entityMapper.toEntity(dto));
        return entityMapper.toDto(review, product.getName());
    }
    /**
     * Возвращает список отзывов по id товара из хранилища.
     *
     * @param productId - id заданного товара
     * @return список всех отзывов, может быть пустым
     * @see ProductReviewRepository#findByProductId(Long)
     */
    public List<ProductReviewDto> getReviewsForProduct(Long productId) {
        List<ProductReview> reviewList = reviewRepository.findByProductId(productId);
        if (reviewList.isEmpty()) {
            return List.of();
        }
        Product product = productService.findProductById(productId);
        return reviewList.stream()
                .map(r -> entityMapper.toDto(r, product.getName()))
                .toList();
    }
}
