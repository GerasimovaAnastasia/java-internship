package dev.gerasimova.exception;

/**
 * Исключение, выбрасываемое когда категория не найдена в системе или иное сообщение об ошибке.
 */
public class CategoryException extends RuntimeException {

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(Long id) {
        super("Категория с ID " + id + " не найдена");
    }

}
