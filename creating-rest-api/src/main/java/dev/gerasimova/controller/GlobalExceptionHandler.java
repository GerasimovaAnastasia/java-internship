package dev.gerasimova.controller;

import dev.gerasimova.dto.ErrorResponse;
import dev.gerasimova.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;


/**
 * Глобальный обработчик исключений для REST API.
 * Перехватывает исключения и возвращает стандартизированные JSON-ответы.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает случаи, когда книга не найдена.
     *
     * @param ex исключение BookException
     * @return ResponseEntity с ошибкой 404
     */
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookException ex) {

        ErrorResponse error = new ErrorResponse(ex.getMessage());
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
     * Обрабатывает все неперехваченные исключения.
     *
     * @param ex исключение
     * @return ResponseEntity с общей ошибкой 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {

        ErrorResponse error = new ErrorResponse("Внутренняя ошибка сервера");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    /**
     * Обрабатывает исключения типа IllegalArgumentException.
     * Используется для обработки бизнес-логических ошибок валидации,
     * таких, как несовпадение ID в пути запроса и теле DTO.
     *
     * @param ex исключение IllegalArgumentException, содержащее сообщение об ошибке
     * @return ResponseEntity с ошибкой 400 Bad Request и деталями валидации
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}