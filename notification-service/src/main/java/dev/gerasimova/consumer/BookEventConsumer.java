package dev.gerasimova.consumer;

import dev.gerasimova.dto.BookCreatedEvent;
import dev.gerasimova.model.Notification;
import dev.gerasimova.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class BookEventConsumer {
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "book_events")
    public void handleBookCreatedEvent(BookCreatedEvent event) {
        log.info("Получен BookCreatedEvent: {}", event);
        try {
            Notification notification = new Notification(event);

            Notification savedNotification = notificationRepository.save(notification);

            log.info("Уведомление сохранено в MongoDB. ID: {}, Book ID: {}",
                    savedNotification.getId(), savedNotification.getBookId());

            log.info("   Book ID: {}", event.getBookId());
            log.info("   Title: {}", event.getTitle());
            log.info("   Author: {}", event.getAuthorSurname());
            log.info("   CreatedAt: {}", event.getCreatedAt());

        } catch (Exception e) {
            log.error("Ошибка при сохранении уведомления в MongoDB: {}", e.getMessage(), e);
        }
    }
}