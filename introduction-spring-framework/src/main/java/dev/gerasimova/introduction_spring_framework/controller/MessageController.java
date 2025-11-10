package dev.gerasimova.introduction_spring_framework.controller;

import dev.gerasimova.introduction_spring_framework.service.MessageService;
import dev.gerasimova.introduction_spring_framework.service.OrderService;
import dev.gerasimova.introduction_spring_framework.service.PrototypeScopeService;
import org.springframework.context.ApplicationContext;
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
    private final ApplicationContext context;

    /**
     * Создает объекты сервисов с бизнес-логикой отправки сообщений,
     * объект spring-контекста.
     * @param messageService - сервис для создания сообщения.
     * @param orderService - сервис для отправки сообщения.
     * @param context - spring-контекст.
     */

    private MessageController(MessageService messageService, OrderService orderService,
                              ApplicationContext context) {
        this.messageService = messageService;
        this.orderService = orderService;
        this.context = context;
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
    /**
     * Endpoint для тестирования жизненного цикла бина prototype.
     * При каждом вызове создается новый экземпляр, поэтому счетчик всегда равен 0.
     *
     * @return - счетчик.
     */
    @GetMapping("/scope")
    public int scopePrototypeBean() {
        PrototypeScopeService prototype =  context.getBean(PrototypeScopeService.class);
        return prototype.getCount();
    }
    /**
     * Endpoint для тестирования жизненного цикла бина prototype.
     * Должен возвращать всегда 1, показывая, что при каждом следующем создании,
     * создается новый бин (prototype) и к нему прибавляется 1.
     *
     * @return - счетчик.
     */
    @GetMapping("/scope-increment")
    public int scopePrototypeWithIncrement() {
        PrototypeScopeService prototype = context.getBean(PrototypeScopeService.class);
        prototype.increment();
        return prototype.getCount();
    }
}
