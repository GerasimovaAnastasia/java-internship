package dev.gerasimova;

/**
 * Класс для реализации арифметических операций (сложения, вычитания, умножения и деления).
 * Поддерживает проверку при делении на ноль.
 */
public class Calculator {

    /**
     * Возвращает сумму двух чисел.
     * @param x первое слагаемое
     * @param y второе слагаемое
     * @return сумма x и y
     */
    public static double add(double x, double y) {
        return  x + y;
    }

    /**
     * Возвращает разность двух чисел.
     * @param x уменьшаемое
     * @param y вычитаемое
     * @return разность x и y
     */
    public static double subtract(double x, double y) {
        return  x - y;
    }

    /**
     * Возвращает произведение двух чисел.
     * @param x первый множитель
     * @param y второй множитель
     * @return произведение x и y
     */
    public static double multiply(double x, double y) {
        return  x * y;
    }
    /**
     * Возвращает результат деления x на y.
     * @param x делимое
     * @param y делитель
     * @return частное x и y, или NaN если y = 0
     */
    public static double divide(double x, double y) {
        double result = Double.NaN;
        if (y != 0.0) {
            return  x / y;
        }
        else {
            System.out.println("На ноль делить нельзя!");
            return result;
        }
    }
}
