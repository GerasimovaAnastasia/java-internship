package dev.gerasimova.controller;

import dev.gerasimova.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с книгами.
 * Обрабатывает HTTP запросы связанные с операциями над книгами.
 */
@RestController
@Tag(name = "Book Controller", description = "API для работы с книгами")
public class BookController {

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
    @Operation(summary = "Get example book", description = "Возвращает тестовую книгу")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/book")
    public Book getExampleBook() {
        return new Book(4L, "Eugene Onegin", "Pushkin", 650.0);
    }
}
