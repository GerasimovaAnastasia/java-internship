package dev.gerasimova.service;

import dev.gerasimova.dto.BookCreatedEvent;
import dev.gerasimova.model.Notification;
import dev.gerasimova.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumerService {
    private final NotificationRepository notificationRepository;
    @Async
    public CompletableFuture<Notification> processBookCreatedEvent(BookCreatedEvent event) {
        try {
            Notification notification = new Notification(event);

            Notification savedNotification = notificationRepository.save(notification);

            log.info("Уведомление асинхронно сохранено в MongoDB. ID: {}, Book ID: {}",
                    savedNotification.getId(), savedNotification.getBookId());

            log.info("   Book ID: {}", event.getBookId());
            log.info("   Title: {}", event.getTitle());
            log.info("   Author: {}", event.getAuthorSurname());
            log.info("   CreatedAt: {}", event.getCreatedAt());
            return CompletableFuture.completedFuture(savedNotification);

        } catch (Exception e) {
            log.error("Ошибка при асинхронном сохранении уведомления в MongoDB: {}", e.getMessage(), e);
            return CompletableFuture.failedFuture(e);
        }
    }
}
