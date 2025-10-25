package dev.gerasimova;


import java.util.Scanner;
/**
 * Работа с одномерными массивами.
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayProcessor.createArray();
        ArrayProcessor.findMaxElementInArray(array);
        ArrayProcessor.findElementInArray(array, scanner);

        scanner.close();
    }
}