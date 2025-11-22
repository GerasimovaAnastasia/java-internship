package dev.gerasimova.controller;

import dev.gerasimova.dto.CreateReviewDto;
import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.dto.ProductReviewDto;
import dev.gerasimova.service.ProductReviewService;
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
 * Контроллер для управления отзывами.
 * Обеспечивает CRUD-операции над сущностями отзывов через REST API.
 *
 * @see dev.gerasimova.model.ProductReview
 */
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ProductReviewService productReviewService;
    /**
     * Endpoint для получения отзывов по идентификатору товара.
     *
     * @param productId уникальный идентификатор продукта
     * @return список отзывов для продукта с заданным идентификатором
     */
    @Operation(summary = "Получить список отзывов по ID продукта", description = "Находит все отзывы о продукте по его идентификатору")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список отзывов",
                    content = @Content(schema = @Schema(implementation = ProductReviewDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Продукт не найден",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @Parameter(name = "productId", description = "ID продукта", example = "1")
    @GetMapping("/reviews/{productId}")
    public ResponseEntity<List<ProductReviewDto>> getReviewsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productReviewService.getReviewsForProduct(productId));
    }
    /**
     * Endpoint для сохранения нового отзыва в хранилище.
     *
     * @param dto DTO с данными для создания отзыва на продукт
     * @return новый сохраненный отзыв
     */
    @Operation(summary = "Сохранение нового отзыва в хранилище",
            description = "Новый отзыв будет добавлен в хранилище")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Отзыв успешно создан",
                    content = @Content(schema = @Schema(implementation = ProductReviewDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные отзыва",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Указанный продукт не существует",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/reviews")
    public ResponseEntity<ProductReviewDto> createReview(@Valid @RequestBody CreateReviewDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productReviewService.addReview(dto));
    }
}
