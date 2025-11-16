package dev.gerasimova.controller;

import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.dto.UpdateBookDto;
import dev.gerasimova.exception.BookException;
import dev.gerasimova.model.Book;
import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

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
                    content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @Parameter(name = "id", description = "ID книги", example = "1")
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = service.finBookById(id)
                .orElseThrow(() -> new BookException(id));
        return ResponseEntity.ok(book);
    }
    /**
     * Endpoint для получения списка книг.
     *
     * @return - список книг.
     */
    @Operation( summary = "Получение всех книг",
            description = "Возвращает все книги.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ: список книг",
                    content = @Content(schema = @Schema(implementation = Book.class))
            )
    })
    @GetMapping("/books/search")
    public ResponseEntity<?> searchBook() {
        return ResponseEntity.ok(service.getAllBooks());
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
                    content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные книги",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликтующие данные",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        Book newBook = createBookDto.toBook();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveBook(newBook));
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
                    content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные книги",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))

            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликтующие данные",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBookById(@Valid @RequestBody UpdateBookDto dto,
                                            @PathVariable Long id) {
        Book existingBook = service.finBookById(id)
                .orElseThrow(() -> new BookException(id));

        dto.updateBook(existingBook);

        Book updatedBook = service.saveBook(existingBook);
        return ResponseEntity.ok(updatedBook);
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
        Book existingBook = service.finBookById(id)
                .orElseThrow( () -> new BookException(id));
        service.deleteBook(existingBook);
        return ResponseEntity.noContent().build();
    }
}
