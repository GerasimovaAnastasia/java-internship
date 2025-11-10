package dev.gerasimova.introduction_spring_framework.controller;

import dev.gerasimova.introduction_spring_framework.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для endpoints, связанных с логикой отправки сообщений,
 * работой с spring-контекстом и конфигурацией.
 *
 */
@RestController
public class MessageController {

    private final MessageService messageService;

    /**
     * Создает объекты сервисов с бизнес-логикой отправки сообщений,
     * объект spring-контекста.
     * @param messageService - сервис для создания сообщения.
     */

    private MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    /**
     * Endpoint для получения сообщения
     *
     * @return - сформированное сообщение
     */
    @GetMapping("/test")
    public String getMessage() {
        return messageService.getMessage();
    }

}
