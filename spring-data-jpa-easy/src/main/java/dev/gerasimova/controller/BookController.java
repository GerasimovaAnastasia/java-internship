package dev.gerasimova.controller;

import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.dto.UpdateBookDto;
import dev.gerasimova.exception.AuthorException;
import dev.gerasimova.exception.BookException;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.service.AuthorService;
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

import java.util.List;

/**
 * Контроллер для endpoints, связанных с сущностями книга и автор книги.
 *
 * @see Book
 * @see Author
 */
@RestController
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
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
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bookService.finBookById(id)
                .orElseThrow(() -> new BookException(id));
        return ResponseEntity.status(HttpStatus.OK).body(BookResponseDto.fromEntity(book));
    }
    /**
     * Endpoint для получения списка книг/списка книг конкретного автора/
     * конкретной книги по названию и автору.
     *
     * @return - список книг.
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
    public ResponseEntity<?> searchBook(@RequestParam(required = false) String authorSurname,
                                        @RequestParam(required = false) String title) {
        boolean hasAuthor = authorSurname != null && !authorSurname.isBlank();
        boolean hasTitle = title != null && !title.isBlank();

        if (hasAuthor && hasTitle) {
            return bookService.findByTitleAndAuthor(title, authorSurname)
                    .map(book -> ResponseEntity.status(HttpStatus.OK).body(BookResponseDto.fromEntity(book)))
                    .orElseThrow(() -> new BookException("Книга с таким названием и автором не найдена!"));
        } else if (hasAuthor) {
            List<BookResponseDto> books = bookService.findByAuthor(authorSurname).stream()
                    .map(BookResponseDto::fromEntity)
                    .toList();
            if (books.isEmpty()) {
                throw new BookException("Книги автора '" + authorSurname + "' не найдены");
            }
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } else {
            List<BookResponseDto> books = bookService.getAllBooks().stream()
                    .map(BookResponseDto::fromEntity)
                    .toList();
            return ResponseEntity.status(HttpStatus.OK).body(books);
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
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Указанный автор не существует",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        Author existingAuthor = authorService.finBookById(createBookDto.authorID())
                .orElseThrow(() -> new AuthorException(createBookDto.authorID()));
        Book newBook = createBookDto.toBook(existingAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookResponseDto.fromEntity(bookService.saveBook(newBook)));
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
        Book existingBook = bookService.finBookById(id)
                .orElseThrow(() -> new BookException(id));
        Author existingAuthor = authorService.finBookById(dto.authorID())
                        .orElseThrow(() -> new AuthorException(dto.authorID()));

        dto.updateBook(existingBook, existingAuthor);

        Book updatedBook = bookService.saveBook(existingBook);
        return ResponseEntity.status(HttpStatus.OK).body(BookResponseDto.fromEntity(updatedBook));
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
        Book existingBook = bookService.finBookById(id)
                .orElseThrow( () -> new BookException(id));
        bookService.deleteBook(existingBook);
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
    public List<BookResponseDto> searchBooksByTitle(@RequestParam String title) {
        return bookService.searchByTitleOrderByYear(title).stream()
                .map(BookResponseDto::fromEntity)
                .toList();
    }
}
