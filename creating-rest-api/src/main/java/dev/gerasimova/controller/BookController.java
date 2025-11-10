package dev.gerasimova.controller;

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
}
