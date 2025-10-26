package dev.gerasimova;

import java.util.Arrays;
import java.util.Scanner;
import java.util.random.RandomGenerator;

/**
 * Класс для работы с одномерными массивами.
 * Поддерживает создание массива, нахождение максимального элемента массива
 * и нахождение индекса заданного элемента в массиве.
 */
public class ArrayProcessor {

    /**
     * Создает и возвращает массив из 20 случайных целых чисел в диапазоне [-1000, 1000].
     * <p>
     * @return новый массив из 20 случайных целых чисел
     */
    public static int[] createArray() {
        int[] array = new int[20];

        for(int i=0; i<20; i++) {
            array[i] = RandomGenerator.getDefault().nextInt(-1000, 1001);
        }
        System.out.println("Массив:");
        System.out.println(Arrays.toString(array));
        return array;
    }

    /**
     * Находит и печатает максимальный элемент массива.
     * <p>
     * @param array массив для поиска максимального элемента
     * @throws IllegalArgumentException если массив null или пустой
     */
    public static void findMaxElementInArray(int[] array) {

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }

        int maxElement = array[0];

        for (int j : array) {
            if (maxElement < j) {
                maxElement = j;
            }
        }
        System.out.printf("Максимальный элемент массива: %d\n", maxElement);
    }
    /**
     * Выполняет поиск элемента в массиве и выводит его индекс.
     * <p>
     * @param array массив для поиска (не должен быть null)
     * @param scanner объект Scanner для ввода искомого элемента
     * @throws IllegalArgumentException если массив null
     */
    public static void findElementInArray(int[] array, Scanner scanner) {

        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        System.out.println("Введите элемент для поиска: ");
        int element = inputElement(scanner);
        int index = -1;

        for (int j =0; j < array.length; j++) {
            if (element == array[j]) {
                index = j;
                break;
            }
        }
        System.out.printf("Индекс заданного элемента: %d\n", index);

        if(index == -1) {
            System.out.println("Элемент в массиве отсутствует!");
        }
    }
    /**
     * Считывает и проверяет целое число из ввода.
     * <p>
     * @param scanner объект Scanner для чтения ввода
     * @return корректное целое число, введенное пользователем
     * @throws NumberFormatException если ввод не может быть преобразован в число
     */
    public static int inputElement(Scanner scanner) {

        boolean validInput = false;
        int element = 0;
        while (!validInput) {
            System.out.println("Введите число: ");
            String inputElement = scanner.nextLine();
            try {
                element = Integer.parseInt(inputElement);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: '" + inputElement + "' не является числом!");
            }
        }
        return element;
    }
}
