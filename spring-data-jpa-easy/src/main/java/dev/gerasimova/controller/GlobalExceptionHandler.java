package dev.gerasimova.controller;

import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.dto.ValidationErrorResponse;
import dev.gerasimova.exception.AuthorException;
import dev.gerasimova.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;


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
     * @return дто ValidationErrorResponse с ошибкой 400 и деталями валидации
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ValidationErrorResponse.FieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationErrorResponse.FieldError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        ValidationErrorResponse response = new ValidationErrorResponse(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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