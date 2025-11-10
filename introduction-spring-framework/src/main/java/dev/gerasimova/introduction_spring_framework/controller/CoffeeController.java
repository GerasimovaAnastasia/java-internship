package dev.gerasimova.introduction_spring_framework.controller;

import dev.gerasimova.introduction_spring_framework.service.CoffeeMachineService;
import dev.gerasimova.introduction_spring_framework.service.DiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Контроллер для endpoints сервиса про кофе.
 *
 * @see CoffeeMachineService
 * @see DiscountService
 */
@RestController
public class CoffeeController {

    private final CoffeeMachineService coffeeMachine;

    private final Optional<DiscountService> discountService;

    /**
     * Создает объекты сервисов для взаимодействия с кофе-машиной и скидками.
     * @param coffeeMachine - объект сервиса для работы с кофе-машиной
     * @param discountService - объект сервиса скидок
     */
    public CoffeeController(CoffeeMachineService coffeeMachine, Optional<DiscountService> discountService) {
        this.coffeeMachine = coffeeMachine;
        this.discountService = discountService;
    }

    /**
     * Endpoint для заказа кофе
     *
     * @return - сообщение о результате приготовления и скидках.
     */
    @GetMapping("/coffee")
    public String makeCoffee() {
        coffeeMachine.makeCoffee();

        if (discountService.isPresent()) {
            return "Кофе готов! И есть скидка 10%!";
        } else {
            return "Кофе готов! (Скидок сегодня нет)";
        }
    }
}
