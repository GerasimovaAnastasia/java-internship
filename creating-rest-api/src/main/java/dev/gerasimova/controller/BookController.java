package dev.gerasimova.controller;

import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.model.Book;
import dev.gerasimova.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Контроллер для работы с книгами.
 * Обрабатывает HTTP запросы связанные с операциями над книгами.
 */
@RestController
@Tag(name = "Book Controller", description = "API для работы с книгами")
public class BookController {
    private final LibraryService libraryService;

    /**
     * Конструктор инициализирует сервис для работы с библиотекой.
     * @param libraryService - сервис-хранилище для работы с библиотекой.
     */

    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    /**
     * Тестовый endpoint для проверки работы приложения.
     *
     * @return приветственное сообщение
     */
    @Operation(summary = "Get welcome message", description = "Возвращает приветствие")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/hello")
    public String getMessage() {
        return "Hello, Spring!";
    }

    /**
     * Тестовый endpoint для вывода тестовой книги
     * @return - книга
     */
    @Operation(summary = "Получить тестовую книгу", description = "Возвращает тестовую книгу")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/book")
    public Book getExampleBook() {
        return new Book(4L, "Eugene Onegin", "Pushkin", 650.0);
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
       Optional<Book> book = libraryService.getBookById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book);
        } else {
            ErrorResponse error = new ErrorResponse("Книга с ID " + id + " не найдена");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
