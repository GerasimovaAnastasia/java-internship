package dev.gerasimova.introduction_spring_framework.repository;

import org.springframework.stereotype.Repository;

/**
 * Класс репозиторий имитирующий взаимодействие с бд.
 */
@Repository
public class MessageRepository {

    /**
     * Метод имитирующий получение сообщения.
     */
    public String findMessage() {
        return "Message from Repository";
    }
}
