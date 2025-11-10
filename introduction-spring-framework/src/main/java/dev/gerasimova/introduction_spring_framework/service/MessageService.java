package dev.gerasimova.introduction_spring_framework.service;

import dev.gerasimova.introduction_spring_framework.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с сообщениями.
 * Демонстрирует внедрение зависимостей и значений из properties-файла.
 *
 * @see MessageRepository
 */
@Service
public class MessageService {
    private final String message ;
    private final MessageRepository messageRepository;

    /**
     * Конструктор внедряет текст сообщения через аннотацию @Value("${app.greeting}") из application.properties.
     * @param str - сообщение
     * @param messageRepository - репозиторий для работы с сущностью "Сообщение"
     */
    public MessageService(@Value("${app.greeting}") String str, MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.message = str;
    }

    /**
     * Метод возвращает сообщение.
     * @return - сообщение
     */
    public String getMessage() {
        return message;
    }

    /**
     * Метод имитирует возвращение информацию из бд через репозиторий.
     * @return - полученное сообщение.
     */
    public String getMessageInfo() {
        String message = messageRepository.findMessage();
        return "Find: " + message;
    }
}
