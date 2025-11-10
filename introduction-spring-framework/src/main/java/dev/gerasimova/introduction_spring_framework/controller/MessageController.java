package dev.gerasimova.introduction_spring_framework.controller;

import dev.gerasimova.introduction_spring_framework.service.MessageService;
import dev.gerasimova.introduction_spring_framework.service.OrderService;
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
    private final OrderService orderService;

    /**
     * Создает объекты сервисов с бизнес-логикой отправки сообщений,
     * объект spring-контекста.
     * @param messageService - сервис для создания сообщения.
     * @param orderService - сервис для отправки сообщения.
     */

    private MessageController(MessageService messageService, OrderService orderService) {
        this.messageService = messageService;
        this.orderService = orderService;
    }
    /**
     * Endpoint для получения сообщения
     *
     * @return - сформированное сообщение
     */
    @GetMapping("/test")
    public String getMessage() {
        return messageService.getMessageInfo();
    }
    /**
     * Endpoint для выбора способа отправки сообщения по умолчанию
     *
     * @return - информация о типе отправки.
     */
    @GetMapping("/send")
    public String sendMessage() {
        return orderService.processOrder();
    }
}
