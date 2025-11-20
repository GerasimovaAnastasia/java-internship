package dev.gerasimova.exception;

/**
 * Исключение, выбрасываемое когда автор не найден в системе или иное сообщение об ошибке.
 */
public class AuthorException  extends RuntimeException {

    public AuthorException(String message) {
        super(message);
    }

    public AuthorException(Long id) {
        super("Автор с ID " + id + " не найден");
    }

}
