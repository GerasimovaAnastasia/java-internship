package dev.gerasimova.introduction_spring_framework.controller;

import dev.gerasimova.introduction_spring_framework.service.CoffeeMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для endpoints сервиса про кофе.
 *
 * @see CoffeeMachineService
 */
@RestController
public class CoffeeController {

    private final CoffeeMachineService coffeeMachine;

    /**
     * Создает объекты сервисов для взаимодействия с кофе-машиной и скидками.
     * @param coffeeMachine - объект сервиса для работы с кофе-машиной
     */
    public CoffeeController(CoffeeMachineService coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    /**
     * Endpoint для заказа кофе
     *
     * @return - сообщение о результате приготовления и скидках.
     */
    @GetMapping("/coffee")
    public String makeCoffee() {
        coffeeMachine.makeCoffee();
        return "Ваш кофе готов!";
    }
}
