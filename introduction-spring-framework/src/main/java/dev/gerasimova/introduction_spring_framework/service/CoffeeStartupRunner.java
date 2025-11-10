package dev.gerasimova.introduction_spring_framework.service;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Компонент, выполняющий код сразу после полной инициализации Spring контекста.
 * Реализует интерфейс CommandLineRunner.
 *
 * @see CommandLineRunner
 */
@Component
public class CoffeeStartupRunner implements CommandLineRunner {

    private final CoffeeMachineService coffeeMachine;

    /**
     * Конструктор с внедрением зависимости сервиса кофе машины.
     *
     * @param coffeeMachine сервис для работы с кофе машиной
     */
    public CoffeeStartupRunner(CoffeeMachineService coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }
    /**
     * Метод вызывается автоматически после полной инициализации Spring контекста.
     * Используется для выполнения стартовых задач приложения.
     *
     * @param args аргументы командной строки (не используются)
     */
    @Override
    public void run(String... args) {
        System.out.println("CommandLineRunner: Приложение запущено!");
        System.out.println("Автозапуск кофемашины...");

        coffeeMachine.makeCoffee();

        System.out.println("Кофе готов! Приложение полностью запущено и можно работать!");
    }
}
