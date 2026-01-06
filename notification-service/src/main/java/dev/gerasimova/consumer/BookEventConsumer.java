package dev.gerasimova.consumer;

import dev.gerasimova.dto.BookCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class BookEventConsumer {

    @KafkaListener(topics = "book_events")
    public void handleBookCreatedEvent(BookCreatedEvent event) {
        log.info("Получен BookCreatedEvent: {}", event);
        log.info("   Book ID: {}", event.getBookId());
        log.info("   Title: {}", event.getTitle());
        log.info("   Author: {}", event.getAuthorSurname());
        log.info("   CreatedAt: {}", event.getCreatedAt());
    }
}