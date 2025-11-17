package dev.gerasimova.controller;

import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.exception.AuthorException;
import dev.gerasimova.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;


/**
 * Глобальный обработчик исключений для REST API.
 * Перехватывает исключения и возвращает стандартизированные JSON-ответы.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает случаи, когда сущность не найдена.
     *
     * @param e исключение BookException
     * @return ResponseEntity с ошибкой 404
     */
    @ExceptionHandler({AuthorException.class, BookException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(RuntimeException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Обрабатывает ошибки валидации @Valid.
     *
     * @param ex исключение MethodArgumentNotValidException
     * @return ResponseEntity с ошибкой 400 и деталями валидации
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse error = new ErrorResponse("Ошибка валидации: " + errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Обрабатывает исключения типа DataIntegrityViolationException.
     * Нужен, когда создаются дупликаты объектов (одинаковые название книги и автор)
     * @param e исключение DataIntegrityViolationException, содержащее сообщение об ошибке
     * @return ResponseEntity с ошибкой 400 Bad Request или с ошибкой 409 - конфликт.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException e) {
        if (e.getMessage().contains("books_unique")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Книга с таким названием и автором уже существует"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Ошибка целостности данных"));
    }
}