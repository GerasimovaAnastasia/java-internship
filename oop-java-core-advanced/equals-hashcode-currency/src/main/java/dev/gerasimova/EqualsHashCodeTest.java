package dev.gerasimova;

import java.util.HashSet;
import java.util.Set;

/**
 * Тестовый класс для проверки корректности реализации методов equals() и hashCode().
 * Проверяет соблюдение контракта между equals() и hashCode() в классе Currency.
 *
 * @see Currency
 * @see Object#equals(Object)
 * @see Object#hashCode()
 */
public final class EqualsHashCodeTest {

    /**
     * Приватный конструктор.
     */
    private EqualsHashCodeTest() {
        throw new UnsupportedOperationException("EqualsHashCodeTest class");
    }

    /**
     * Запускает все тесты для задачи по equals() и hashCode().
     *
     * @see #shouldReturnTrueWhenSameCode()
     * @see #shouldReturnFalseWhenDifferentCode()
     * @see #shouldHaveSameHashCodeForEqualObjects()
     */
    public static void runEqualsHashCodeTests() {
        String[] descriptions = {
                "Объекты равны при одинаковом коде",
                "Объекты не равны при разных кодах",
                "HashCode одинаков для равных объектов"
        };

        TestMethod[] tests = {
                EqualsHashCodeTest::shouldReturnTrueWhenSameCode,
                EqualsHashCodeTest::shouldReturnFalseWhenDifferentCode,
                EqualsHashCodeTest::shouldHaveSameHashCodeForEqualObjects
        };

        TestRunnerUtil.runTestSuite("ЗАДАЧА 2: equals() и hashCode()", descriptions, tests);
    }
    /**
     * Проверяет, что объекты Currency считаются равными при одинаковом коде.
     * Разные названия валют не должны влиять на результат сравнения.
     *
     * @return true если объекты равны, false в противном случае
     * @see Currency#equals(Object)
     */
    public static boolean shouldReturnTrueWhenSameCode() {
        Currency currency1 = new Currency("Dollar", "USD");
        Currency currency2 = new Currency("US Dollar", "USD");

        Set<Currency> currencies = new HashSet<>();
        currencies.add(currency1);

        boolean containsWorks = currencies.contains(currency2);
        System.out.println("Валюта с одинаковым кодом: " + containsWorks);
        return containsWorks;
    }
    /**
     * Проверяет, что объекты Currency считаются разными при разных кодах.
     * Одинаковые названия валют не должны влиять на результат сравнения.
     *
     * @return true если объекты не равны, false в противном случае
     * @see Currency#equals(Object)
     */
    public static boolean shouldReturnFalseWhenDifferentCode() {
        Currency currency1 = new Currency("RUB", "643");
        Currency currency2 = new Currency("US Dollar", "USD");

        Set<Currency> currencies = new HashSet<>();
        currencies.add(currency1);

        boolean containsWorks = currencies.contains(currency2);
        System.out.println("Валюта с разными кодомами: " + containsWorks);
        return !containsWorks;
    }
    /**
     * Проверяет соблюдение контракта между equals() и hashCode().
     * Если два объекта равны по equals(), их hashCode() должны быть одинаковыми.
     *
     * @return true если hashCode одинаков для равных объектов, false в противном случае
     * @see Currency#hashCode()
     * @see Currency#equals(Object)
     */
    public static boolean shouldHaveSameHashCodeForEqualObjects() {
        Currency currency1 = new Currency("Dollar", "USD");
        Currency currency2 = new Currency("US Dollar", "USD");

        boolean sameHashCode = currency1.hashCode() == currency2.hashCode();
        System.out.println("HashCode для равных объектов: " + sameHashCode);
        return sameHashCode;
    }
}
