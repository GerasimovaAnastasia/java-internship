package dev.gerasimova;

/**
 * Тестовый класс для проверки инкапсуляции и валидации данных в классе BankAccount.
 * Содержит тесты для проверки корректной работы сеттеров баланса и имени с валидацией.
 *
 * @see BankAccount
 */
public final class EncapsulationTest {

    /**
     * Приватный конструктор.
     */
    private EncapsulationTest() {
        throw new UnsupportedOperationException("EncapsulationTest class");
    }

    /**
     * Запускает все тесты для задачи по инкапсуляции и валидации.
     *
     * @see #shouldAcceptValidBalance()
     * @see #shouldThrowExceptionWhenNameIsEmpty()
     * @see #shouldThrowExceptionWhenBalanceIsNegative()
     */
    public static void runEncapsulationTests() {
        String[] descriptions = {
                "Принимает корректное имя",
                "Принимает корректный баланс",
                "Бросает исключение при отрицательном балансе",
                "Бросает исключение при пустом имени"
        };

        TestMethod[] tests = {
                EncapsulationTest::shouldAcceptValidName,
                EncapsulationTest::shouldAcceptValidBalance,
                EncapsulationTest::shouldThrowExceptionWhenBalanceIsNegative,
                EncapsulationTest::shouldThrowExceptionWhenNameIsEmpty
        };

        TestRunnerUtil.runTestSuite("ЗАДАЧА 1: Инкапсуляция и валидация", descriptions, tests);
    }
    /**
     * Проверяет, что сеттер принимает корректные значения имени.
     *
     * @return true если имя принято успешно, false в противном случае
     * @see BankAccount#setOwnerName(String)
     */
    public static boolean shouldAcceptValidName() {

        return testInputName("Sonya", true);
    }
    /**
     * Проверяет, что сеттер принимает корректные значения баланса (не может быть отрицательным).
     *
     * @return true если баланс не отрицательный, false в противном случае
     * @see BankAccount#setBalance(double)
     */
    public static boolean shouldAcceptValidBalance() {

        return testInputBalance(300000, true);
    }
    /**
     * Проверяет, что сеттер выбрасывает исключение при пустом имени.
     *
     * @return true если исключение выброшено корректно, false в противном случае
     * @see BankAccount#setOwnerName(String)
     */
    public static boolean shouldThrowExceptionWhenNameIsEmpty() {

        return testInputName(" ", false);
    }
    /**
     * Проверяет, что сеттер выбрасывает исключение при отрицательном балансе.
     *
     * @return true если исключение выброшено корректно, false в противном случае
     * @see BankAccount#setBalance(double)
     */
    public static boolean shouldThrowExceptionWhenBalanceIsNegative() {
        return testInputBalance(-1, false);
    }

    /**
     * Вспомогательный метод для тестирования валидации баланса.
     *
     * @param balance баланс для тестирования
     * @param shouldSucceed true если баланс должен быть принят, false если должно быть выброшено исключение
     * @return true если результат соответствует ожиданиям, false в противном случае
     */
    public static boolean testInputBalance(double balance, boolean shouldSucceed) {

        System.out.println(STR."Введен баланс: \{balance}");
        try {
            BankAccount account = new BankAccount("Maria", balance);
            return shouldSucceed;
        } catch (IllegalArgumentException e) {
            System.out.println(STR."Ошибка: \{e.getMessage()}");
            return !shouldSucceed;
        }
    }

    /**
     * Вспомогательный метод для тестирования валидации имени.
     *
     * @param name имя для тестирования
     * @param shouldSucceed true если имя должно быть принято, false если должно быть выброшено исключение
     * @return true если результат соответствует ожиданиям, false в противном случае
     */
    public static boolean testInputName(String name, boolean shouldSucceed) {

        System.out.println(STR."Введено имя: \{name}");
        try {
            BankAccount account = new BankAccount(name, 21000);
            return shouldSucceed;
        } catch (IllegalArgumentException e) {
            System.out.println(STR."Ошибка: \{e.getMessage()}");
            return !shouldSucceed;
        }
    }
}
