package dev.gerasimova;

import dev.gerasimova.service.ServiceValidation;

/**
 * Точка входа приложения.
 */
public class Application {
    public static void main(String[] args) {

        System.out.println("Консольное приложение: ");

        try {
            System.out.println("Результат: " + ServiceValidation.validationVersionForJacksonLib());
        } catch (Exception e) {
            System.out.println("Ошибка!");
        }
        System.out.println("Результат: " + ServiceValidation.validationGuava());

        System.out.println("Приложение завершено!");
    }
}