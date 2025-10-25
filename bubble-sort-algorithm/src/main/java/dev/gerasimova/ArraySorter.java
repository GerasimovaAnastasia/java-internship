package dev.gerasimova;

import java.util.Arrays;
import java.util.random.RandomGenerator;

/**
 * Класс для работы с одномерными массивами.
 * Содержит алгоритм пузырьковой сортировки&
 */
public class ArraySorter {

    /**
     * Реализует алгоритм пузырьковой сортировки (Bubble Sort) для массива целых чисел.
     * Сортировка выполняется по возрастанию. Исходный массив не изменяется.
     * <p>
     * @param array массив для дальнейшей сортировки.
     * @return новый отсортированный по возрастанию массив.
     * @throws IllegalArgumentException если передан null массив
     */
    public static int[] bubbleSort(int[] array) {

        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        int[] sortedArray = Arrays.copyOf(array, array.length);
        boolean swapped;

        for (int i=0; i<array.length-1; i++) {
            swapped = false;
            for (int j=0; j<array.length-1-i; j++) {
                if (sortedArray[j] > sortedArray[j+1]) {

                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j+1];
                    sortedArray[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return sortedArray;
    }
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
}
