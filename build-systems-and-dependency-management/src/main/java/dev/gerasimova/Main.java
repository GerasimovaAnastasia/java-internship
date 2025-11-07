package dev.gerasimova;

import com.google.common.base.Strings;

public class Main {
    public static void main(String[] args) {
        String str = "Hello!";

        if (Main.isValidUserInput(str)) {
            System.out.println("Строка не пустая!");
        }
    }
    public static boolean isValidUserInput(String input) {
        return !Strings.isNullOrEmpty(input);
    }
}