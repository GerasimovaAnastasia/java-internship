package dev.gerasimova.consumer;

import dev.gerasimova.dto.BookCreatedEvent;
import dev.gerasimova.service.NotificationConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookEventConsumer {
    private final NotificationConsumerService notificationConsumerService;
    @KafkaListener(topics = "book_events")
    public void handleBookCreatedEvent(BookCreatedEvent event) {
        log.info("Получен BookCreatedEvent: {}", event);
        CompletableFuture<Void> future = notificationConsumerService
                .processBookCreatedEvent(event)
                .thenAccept(savedNotification -> {
                    log.info("Асинхронная задача успешно завершена для Book ID: {}",
                            savedNotification.getBookId());
                })
                .exceptionally(throwable -> {
                    log.error("Ошибка асинхронной задачи: {}", throwable.getMessage());
                    return null;
                });
    }
}