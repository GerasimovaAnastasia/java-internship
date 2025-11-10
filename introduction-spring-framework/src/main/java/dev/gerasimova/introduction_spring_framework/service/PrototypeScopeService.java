package dev.gerasimova.introduction_spring_framework.service;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
/**
 * Сервис для демонстрации работы prototype-бинов в Spring.
 * При каждом обращении к контексту создается новый экземпляр.
 *
 * @see Scope
 */
@Service
@Getter
@Scope("prototype")
public class PrototypeScopeService {

    private int count = 0;
    /**
     * Конструктор prototype-бина.
     * Вызывается при каждом создании нового экземпляра.
     */
    public PrototypeScopeService() {
        System.out.println("Создан новый PrototypeScopeService");
    }
    /**
     * Увеличивает значение счетчика на 1.
     * Изменения затрагивают только текущий экземпляр бина.
     */
    public void increment() {
        count++;
    }
}
