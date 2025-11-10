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

    /**
     * Конструктор внедряет текст сообщения через аннотацию @Value("${app.greeting}") из application.properties.
     * @param str - сообщение
     */
    public MessageService(@Value("${app.greeting}") String str) {
        this.message = str;
    }

    /**
     * Метод возвращает сообщение.
     * @return - сообщение
     */
    public String getMessage() {
        return message;
    }

}
