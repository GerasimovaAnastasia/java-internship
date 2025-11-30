package dev.gerasimova.exception;

/**
 * Исключение, выбрасываемое когда пользователь не найден в системе или иное сообщение об ошибке.
 */
public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
