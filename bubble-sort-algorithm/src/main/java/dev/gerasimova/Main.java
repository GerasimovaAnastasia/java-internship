package dev.gerasimova;

import java.util.Arrays;

/**
 * Работа с одномерными массивами. Сортировка пузырьком.
 */
public class Main {
    public static void main(String[] args) {

        int[] array = ArraySorter.createArray();
        int[] sortedArray = ArraySorter.bubbleSort(array);
        System.out.println("Отсортированный массив:\n" + Arrays.toString(sortedArray));
    }
}