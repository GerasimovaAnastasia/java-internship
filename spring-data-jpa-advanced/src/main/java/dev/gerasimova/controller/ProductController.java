package dev.gerasimova.controller;

import dev.gerasimova.dto.*;
import dev.gerasimova.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления продуктами.
 * Обеспечивает CRUD-операции над сущностями продуктов через REST API.
 *
 * @see dev.gerasimova.model.Product
 * @see dev.gerasimova.model.Category
 */
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * Endpoint для получения продукта по идентификатору.
     *
     * @param id уникальный идентификатор продукта
     * @return продукт с заданным идентификатором
     */
    @Operation(summary = "Получить продукт по ID", description = "Находит продукт по его идентификатору")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Продукт найден",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Продукт не найден",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @Parameter(name = "id", description = "ID продукта", example = "1")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Endpoint для получения списка всех продуктов.
     *
     * @return список всех продуктов
     */
    @Operation(summary = "Получение всех продуктов", description = "Возвращает список всех продуктов")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))
            )
    })
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponseDto>> searchProduct(@RequestParam(required = false) Long categoryId,
                                                                  @RequestParam(required = false) String title) {
        return ResponseEntity.ok(productService.searchProducts(categoryId, title));
    }

    /**
     * Endpoint для сохранения нового продукта в хранилище.
     *
     * @param createProductDto DTO с данными для создания нового продукта
     * @return новый сохраненный продукт
     */
    @Operation(summary = "Сохранение нового продукта в хранилище",
            description = "Новый продукт будет добавлен в хранилище")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Продукт успешно создан",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные продукта",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Указанная категория не существует",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(createProductDto));
    }

    /**
     * Endpoint для обновления данных о продукте.
     *
     * @param dto новые данные о продукте
     * @param id уникальный идентификатор продукта для обновления
     * @return продукт с обновленными данными
     */
    @Operation(summary = "Обновление информации о продукте",
            description = "Продукт с обновленными данными будет сохранен в хранилище")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Продукт успешно обновлен",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Продукт или категория не найдены",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные продукта",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProductById(@Valid @RequestBody UpdateProductDto dto,
                                                                @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(dto, id));
    }

    /**
     * Удаляет продукт из хранилища по его идентификатору.
     *
     * @param id уникальный идентификатор продукта для удаления
     * @return ResponseEntity с HTTP статусом 204 No Content
     */
    @Operation(summary = "Удаление продукта из хранилища", description = "Продукт удален из хранилища")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Продукт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
