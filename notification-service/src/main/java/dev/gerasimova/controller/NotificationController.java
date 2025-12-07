package dev.gerasimova.controller;

import dev.gerasimova.dto.NotificationRequest;
import dev.gerasimova.dto.NotificationResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Контроллер для endpoints, связанных с отправкой уведомлений.
 */
@Slf4j
@RestController
public class NotificationController {
    /**
     * Endpoint принимает информацию и сообщает о получении сообщения.
     * @param request - уведомление
     * @return - информация, что запрос получен и поставлен в очередь.
     */
    @PostMapping("/notify")
    public NotificationResponse sendNotification(
            @Valid @RequestBody NotificationRequest request) {

        String message = String.format(
                "Уведомление отправлено от %s: %s",
                request.username(),
                request.message()
        );

        log.info("[NOTIFICATION] " + message);

        return new NotificationResponse(
                "SUCCESS",
                "Уведомление поставлено в очередь " + request.username(),
                UUID.randomUUID().toString()
        );
    }

    /**
     * Endpoint для тестирования подключения Сервиса
     * @return - сообщение об удачном запуске сервиса
     */
    @GetMapping("/healthNotification")
    public String health() {
        return "Служба уведомлений запущена!";
    }
}
