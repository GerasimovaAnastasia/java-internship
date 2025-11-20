package dev.gerasimova.exception;

/**
 * Исключение, выбрасываемое когда товар не найден в системе или иное сообщение об ошибке.
 */
public class ProductException extends RuntimeException {

    public ProductException(String message) {
        super(message);
    }

    public ProductException(Long id) {
        super("Продукт с ID " + id + " не найден");
    }

}
