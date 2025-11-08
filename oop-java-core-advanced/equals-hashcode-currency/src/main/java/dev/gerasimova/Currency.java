package dev.gerasimova;

import java.util.Objects;
/**
 * Представляет валюту с кодом и названием.
 * Реализует контракт equals/hashCode для корректной работы с коллекциями.
 */
public class Currency {
    private String name;
    private String code;

    /**
     * Создает объект валюты с указанными названием и кодом.
     *
     * @param name название валюты
     * @param code код валюты
     * @throws IllegalArgumentException если название или код пустые
     */
    public Currency(String name, String code) {
        setCode(code);
        setName(name);
    }
    /**
     * Возвращает название валюты.
     *
     * @return название валюты
     */
    public String getName() {
        return name;
    }
    /**
     * Устанавливает название валюты.
     *
     * @param name название валюты
     * @throws IllegalArgumentException если название пустое
     */
    public void setName(String name) {
        if (!name.isBlank() ) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Название не может быть пустым!");
        }
    }
    /**
     * Возвращает код валюты.
     *
     * @return код валюты
     */
    public String getCode() {
        return code;
    }

    /**
     * Устанавливает код валюты.
     *
     * @param code код валюты
     * @throws IllegalArgumentException если код пустой
     */
    public void setCode(String code) {
        if (!code.isBlank() ) {
            this.code = code;
        } else {
            throw new IllegalArgumentException("Код не может быть пустым!");
        }
    }
    /**
     * Сравнивает объекты валюты по коду.
     * Две валюты считаются равными, если их коды совпадают.
     *
     * @param o объект для сравнения
     * @return true если валюты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }
    /**
     * Возвращает хэш-код валюты на основе кода.
     * Соответствует контракту equals/hashCode.
     *
     * @return хэш-код валюты
     */
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
