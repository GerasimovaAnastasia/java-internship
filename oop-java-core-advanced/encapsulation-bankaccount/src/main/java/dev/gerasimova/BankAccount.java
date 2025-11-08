package dev.gerasimova;
/**
 * Представляет банковский счет с валидацией данных.
 * Обеспечивает инкапсуляцию и защиту состояния объекта.
 */
public class BankAccount {

    private String ownerName;
    private double balance;

    /**
     * Создает банковский счет с указанными владельцем и балансом.
     *
     * @param ownerName имя владельца счета
     * @param balance начальный баланс счета
     * @throws IllegalArgumentException если имя пустое или баланс отрицательный
     */
    public BankAccount(String ownerName, double balance) {
        setOwnerName(ownerName);
        setBalance(balance);
    }
    /**
     * Устанавливает имя владельца счета.
     *
     * @param ownerName имя владельца счета
     * @throws IllegalArgumentException если имя пустое
     */
    public void setOwnerName(String ownerName) {
        if (!ownerName.isBlank()) {
            this.ownerName = ownerName;
        } else {
            throw new IllegalArgumentException("Имя не может быть пустым!");
        }
    }

    /**
     * Устанавливает баланс счета.
     *
     * @param balance новый баланс счета
     * @throws IllegalArgumentException если баланс отрицательный
     */
    public void setBalance(double balance) {
        if (balance >= 0 ) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Баланс должен быть в пределах от положительным!");
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
