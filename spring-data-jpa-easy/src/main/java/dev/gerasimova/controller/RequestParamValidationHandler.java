package dev.gerasimova.controller;


import dev.gerasimova.dto.ValidationErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
/**
 * Глобальный обработчик исключений для REST API.
 * Перехватывает исключения и возвращает стандартизированные JSON-ответы
 * для ошибки валидации @Valid для RequestParam.
 *
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
 */
@ControllerAdvice
public class RequestParamValidationHandler extends ResponseEntityExceptionHandler {
    /**
     * Обрабатывает ошибки валидации @Valid для RequestParam.
     * Перехватывает ConstraintViolationException и возвращает структурированный ответ
     * с информацией о всех нарушениях валидации.
     *
     * @param ex исключение ConstraintViolationException, содержащее информацию о нарушениях
     * @param request объект WebRequest текущего HTTP запроса
     * @return ResponseEntity с HTTP 400 и детализированной информацией об ошибках валидации
     *
     * @see ConstraintViolationException
     * @see ValidationErrorResponse
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleRequestParamValidation(
            ConstraintViolationException ex, WebRequest request) {
        List<ValidationErrorResponse.FieldError> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String fieldName = extractFieldName(violation.getPropertyPath().toString());
                    return new ValidationErrorResponse.FieldError(fieldName, violation.getMessage());
                })
                .toList();

        ValidationErrorResponse response = new ValidationErrorResponse(errors);
        return handleExceptionInternal(ex, response, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }
    /**
     * Извлекает имя параметра из полного пути валидации.
     * Убирает префикс имени метода, оставляя только название параметра.
     *
     * @param propertyPath путь в формате "methodName.parameterName"
     * @return чистое имя параметра
     */
    private String extractFieldName(String propertyPath) {
        if (propertyPath.contains(".")) {
            return propertyPath.substring(propertyPath.lastIndexOf(".") + 1);
        }
        return propertyPath;
    }
}