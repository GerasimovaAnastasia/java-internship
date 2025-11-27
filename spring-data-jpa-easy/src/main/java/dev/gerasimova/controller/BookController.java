package dev.gerasimova.controller;

import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.dto.UpdateBookDto;
import dev.gerasimova.dto.ValidationErrorResponse;
import dev.gerasimova.dto.CreateBookWithAuthorDto;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import dev.gerasimova.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Контроллер для endpoints, связанных с сущностями книга и автор книги.
 *
 * @see Book
 * @see Author
 */
@RestController
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookService bookService;

    /**
     * Endpoint для получения книги по id.
     *
     * @param id - уникальный идентификатор книги.
     * @return - книгу, с заданным идентификатором.
     */
    @Operation(summary = "Получить книгу по ID", description = "Находит книгу по её идентификатору")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Книга найдена",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @Parameter(name = "id", description = "ID книги", example = "1")
    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    /**
     * Endpoint для получения списка книг/списка книг конкретного автора/
     * конкретной книги по названию и автору с пагинацией.
     *
     * @return - List с одной книгой (по автору и названию)
     *      - Пустой список
     *      - Список всех книг.
     */
    @Operation( summary = "Получение всех книг/книг только одного автора/конкретной книги по автору и названию ",
            description = "Возвращает все книги/книги конкретного автора/ одну книгу.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Сущность не найдена (книга или автор)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/books/search")
    public ResponseEntity<Page<BookResponseDto>> searchBook(@Parameter(description = "Фамилия автора")
                                                                 @RequestParam(required = false) String authorSurname,

                                                             @Parameter(description = "Название книги")
                                                                 @RequestParam(required = false) String title,

                                                             @Parameter(description = "Номер страницы")
                                                                @RequestParam(defaultValue = "0") @Min(0) int page,

                                                             @Parameter(description = "Размер страницы")
                                                                 @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,

                                                             @Parameter(description = "Сортировка", examples = {
                                                                     @ExampleObject(name = "По названию", value = "title"),
                                                                     @ExampleObject(name = "По цене DESC", value = "price,desc"),
                                                                     @ExampleObject(name = "По автору ASC", value = "author,asc")
                                                             })
                                                                 @RequestParam(required = false) String sort) {

        return ResponseEntity.ok(bookService.searchBook(authorSurname, title, bookService.convertURLtoPageable(page, size, sort)));
    }
    /**
     * Endpoint для сохранения новой книги в хранилище.
     *
     * @param createBookDto - дто для сохранения введенных данных о новой книге.
     * @return - новая сохраненная книга.
     */
    @Operation(summary = "Сохранение новой книги в хранилище",
            description = "Новая книга будет добавлена в хранилище")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Успешный ответ: книга успешно сохранена",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные книги",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликтующие данные",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Указанный автор не существует",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/books")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(createBookDto));
    }
    /**
     * Метод обновляет данные о книге, находя ее по id.
     *
     * @param dto - новые данные о книге.
     * @param id - уникальный идентификатор для поиска книги.
     * @return - книга с обновленными методами.
     */
    @Operation(summary = "Сохранение обновленной информации о книги в хранилище",
            description = "Книга с обновленными данными будет добавлена в хранилище")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ: книга успешно изменена",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Сущность не найдена (книга или автор)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные книги",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))

            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликтующие данные",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseDto> updateBookById(@Valid @RequestBody UpdateBookDto dto,
                                            @PathVariable Long id) {
        return ResponseEntity.ok(bookService.updateBook(dto, id));
    }
    /**
     * Удаляет книгу из хранилища по её идентификатору.
     *
     * @param id уникальный идентификатор книги для удаления
     * @return ResponseEntity<Void> с HTTP статусом:
     *         - 204 No Content если книга успешно удалена
     *         - 404 Not Found если книга с указанным ID не найдена
     */
    @Operation(summary = "Удаление книги из хранилища", description = "Книга удалена из хранилища")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Книга успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Книга не найдена")
    })
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    /**
     * Endpoint для получения списка книг по части названия книги, без учета регистра,
     * отсортированный по году издания.
     *
     * @return - список книг.
     */
    @Operation( summary = "Получение книг по части названия ",
            description = "Возвращает все книги по части названия, сортирует по году издания.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            )
    })
    @GetMapping("/books/search/title")
    public  ResponseEntity<List<BookResponseDto>> searchBooksByTitle(@RequestParam String title) {
        return  ResponseEntity.ok(bookService.searchByTitleOrderByYear(title));
    }
    /**
     * Endpoint для сохранения книги и автора книги.
     * Выполняется rollback, если выпало исключение.
     *
     * @return - новую книгу.
     */
    @Operation( summary = "Сохранение книги и автора",
            description = "Сохраняет две сущности сразу: новую книгу и нового автора.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные книги или автора",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))

            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт сущностей",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/books/author")
    public ResponseEntity<BookResponseDto> createBookWithAuthor(@Valid @RequestBody CreateBookWithAuthorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBookWithAuthor(dto));
    }

}
