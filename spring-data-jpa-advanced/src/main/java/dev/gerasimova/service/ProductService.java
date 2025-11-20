package dev.gerasimova.service;

import dev.gerasimova.dto.CreateProductDto;
import dev.gerasimova.dto.ProductResponseDto;
import dev.gerasimova.dto.UpdateProductDto;
import dev.gerasimova.exception.CategoryException;
import dev.gerasimova.exception.ProductException;
import dev.gerasimova.model.Category;
import dev.gerasimova.model.Product;
import dev.gerasimova.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Сервисный класс для управления бизнес-логикой работы с продуктами.
 * Обеспечивает CRUD-операции над сущностью Product через ProductRepository.
 *
 * @see dev.gerasimova.model.Product
 * @see dev.gerasimova.repository.ProductRepository
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final EntityMapper entityMapper;

    /**
     * Находит товар по идентификатору.
     *
     * @param id идентификатор товара для поиска
     * @return выводит дто для пользователей с информацией о продукте
     * @see ProductRepository#findById(Object)
     */
    public ProductResponseDto getProductById(Long id) {
        Product product = findProductById(id);
        return entityMapper.toDto(product);
    }

    /**
     * Находит продукт по идентификатору.
     *
     * @param id идентификатор продукта для поиска
     * @return продукт
     * @see ProductRepository#findById(Object)
     */
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException(id));
    }

    /**
     * Сохраняет или обновляет товар в хранилище.
     *
     * @param dto товара для сохранения
     * @return дто товара
     * @see ProductRepository#save(Object)
     */
    public ProductResponseDto saveProduct(CreateProductDto dto) {
        Category category = categoryService.findCategoryById(dto.categoryID())
                .orElseThrow(() -> new CategoryException(dto.categoryID()));
        Product product = entityMapper.toEntity(dto);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return entityMapper.toDto(savedProduct);
    }

    /**
     * Обновляет продукт в хранилище.
     *
     * @param dto товара для обновления
     * @return дто сохраненной продукта
     * @see ProductRepository#save(Object)
     */
    public ProductResponseDto updateProduct(UpdateProductDto dto, Long id) {
        Product existingProduct = findProductById(id);
        Category category = categoryService.findCategoryById(dto.categoryID())
                .orElseThrow(() -> new CategoryException(dto.categoryID()));

        existingProduct.setName(dto.name());
        existingProduct.setCategory(category);
        existingProduct.setPrice(dto.price());

        Product savedProduct = productRepository.save(existingProduct);
        return entityMapper.toDto(savedProduct);
    }

    /**
     * Удаляет товар из хранилища.
     *
     * @param id - уникальный идентификатор товар для удаления
     * @see ProductRepository#delete(Object)
     */
    public void deleteProduct(Long id) {
        productRepository.delete(findProductById(id));
    }

    /**
     * Возвращает список всех товаров из хранилища.
     *
     * @return список всех товаров, может быть пустым
     * @see ProductRepository#findAllByCategory()
     */
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAllByCategory().stream()
                .map(entityMapper::toDto)
                .toList();
    }
    /**
     * Возвращает список товаров по части названия товара без учета регистра и по id категории.
     *
     * @param searchText - часть названия
     * @param categoryId - id категории
     * @return список товаров
     * @see ProductRepository#findProductsByNameAndCategory(String, Long)
     */
    public List<ProductResponseDto> searchByTitleAndCategory(String searchText, Long categoryId) {
        return productRepository.findProductsByNameAndCategory(searchText, categoryId).stream()
                .map(entityMapper::toDto)
                .toList();
    }
    /**
     * Метод для получения списка товаров/списка товаров конкретной категории
     *
     * @return - список товаров или пустой список.
     */
    public List<ProductResponseDto> searchProducts(Long categoryId, String title) {
        boolean hasCategory = categoryId != null;
        boolean hasTitle = title != null && !title.isBlank();

        if (hasCategory && hasTitle) {
            return searchByTitleAndCategory(title, categoryId);
        } else {
            return getAllProducts();
        }
    }
}