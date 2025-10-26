package dev.gerasimova;

import java.util.Scanner;
/**
 * Работа со строками.
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str = scanner.nextLine();

        if (!StringProcessor.isStringEmpty(str)) {
            StringProcessor.countVowelsAndConsonants(str);
            StringProcessor.wordCount(str);
        }

        scanner.close();
    }
}