package dev.gerasimova.repository;

import dev.gerasimova.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByBookId(Long bookId);
    List<Notification> findByStatus(String status);
}