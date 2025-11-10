package dev.gerasimova.introduction_spring_framework.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Сервис, реализующий бизнес-логику скидок.
 * Создается только при наличии свойства feature.toggle.enable=true в application.properties.
 *
 * @see ConditionalOnProperty
 */
@Service
@ConditionalOnProperty(name="feature.toggle.enable", havingValue="true")
public class DiscountService {

    /**
     * Конструктор по умолчанию.
     */
    public DiscountService() {
    }
}
