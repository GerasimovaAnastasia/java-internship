package dev.gerasimova.exception;

/**
 * Исключение, выбрасываемое когда книга не найдена в системе.
 */
public class BookException extends RuntimeException {

    public BookException(String message) {
        super(message);
    }

    public BookException(Long id) {
        super("Книга с ID " + id + " не найдена");
    }
}
