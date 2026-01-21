package dev.gerasimova.controller;

import dev.gerasimova.dto.NotificationRequest;
import dev.gerasimova.dto.NotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Notification Service", description = "API для управления уведомлениями в книжном магазине")
public class NotificationController {
    /**
     * Endpoint принимает информацию и сообщает о получении сообщения.
     * @param request - уведомление
     * @return - информация, что запрос получен и поставлен в очередь.
     */
    @Operation(
            summary = "Отправить уведомление",
            description = "Принимает уведомление и ставит его в очередь на обработку"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Уведомление успешно принято",
                    content = @Content(schema = @Schema(implementation = NotificationResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные запроса"
            )
    })
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
     * Проверка работоспособности сервиса.
     *
     * @return статус сервиса
     */
    @Operation(
            summary = "Health check",
            description = "Проверяет, что сервис запущен и работает"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Сервис работает нормально"
    )
    @GetMapping("/healthNotification")
    public String health() {
        return "Служба уведомлений запущена!";
    }
}