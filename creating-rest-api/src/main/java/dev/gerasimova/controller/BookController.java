package dev.gerasimova.controller;

import dev.gerasimova.dto.CreateBookDto;
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
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
       Book book = libraryService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    /**
     * Endpoint для получения списка книг или конкретной книги по названию.
     *
     * @param title - название книги.
     * @return - книгу, с заданным названием / список книг, если параметр title не указан.
     */
    @Operation( summary = "Поиск книги по названию или получение всех книг",
            description = "Если параметр title не указан - возвращает все книги. Если указан - ищет по точному совпадению названия.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ: список книг или одна книга",
                    content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @Parameter(name = "title", description = "Название книги для поиска", example = "Transformation")
    @GetMapping("/books/search")
    public ResponseEntity<?> searchBook(@RequestParam(required = false) String title) {
        if (title == null || title.isBlank()) {
            return ResponseEntity.ok(libraryService.getAllBooks());
        } else {
            Book book = libraryService.getBookByTitle(title);
            return ResponseEntity.ok(book);
        }
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
            )
    })
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody @Valid CreateBookDto createBookDto) {
        Book newBook = createBookDto.toBook();
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.saveBook(newBook));
    }
    /**
     * Метод обновляет данные о книге, находя ее по id.
     *
     * @param createBookDto - новые данные о книге.
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
                    responseCode = "400",
                    description = "Невалидные данные книги",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Книга не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBookById(@RequestBody @Valid CreateBookDto createBookDto,
                                            @PathVariable Long id) {
        libraryService.getBookById(id);

        if (!Objects.equals(id, createBookDto.id())) {
            throw new IllegalArgumentException("ID в пути и в теле запроса не совпадают");
        }

        Book updatedBook = libraryService.saveBook(createBookDto.toBook());
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
        libraryService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
