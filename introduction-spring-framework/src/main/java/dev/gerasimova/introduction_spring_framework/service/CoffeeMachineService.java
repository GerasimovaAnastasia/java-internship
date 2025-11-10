package dev.gerasimova.introduction_spring_framework.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

/**
 * Сервис для управления кофе-машиной.
 * Демонстрирует жизненный цикл бина Spring.
 */
@Service
public class CoffeeMachineService {

    /**
     * Конструктор по умолчанию.
     * Вызывается при создании бина Spring'ом.
     */
    public CoffeeMachineService() {
        System.out.println("Конструктор: Кофемашина произведена");
    }
    /**
     * Метод инициализации бина.
     * Вызывается Spring'ом после создания бина и внедрения зависимостей.
     *
     * @see PostConstruct
     */
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: Кофемашина включена и прогревается!");
    }
    /**
     * Метод очистки ресурсов бина.
     * Вызывается Spring'ом перед уничтожением бина.
     *
     * @see PreDestroy
     */
    @PreDestroy
    public void cleanup() {
        System.out.println("@PreDestroy: Кофемашина выключена, промыта и готова к хранению");
    }
    /**
     * Запускает процесс приготовления кофе.
     */
    public void makeCoffee() {
        System.out.println("Кофемашина варит кофе...");
    }
}
