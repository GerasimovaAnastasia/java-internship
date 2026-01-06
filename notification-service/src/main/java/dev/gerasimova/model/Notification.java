package dev.gerasimova.model;

import dev.gerasimova.dto.BookCreatedEvent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    
    private Long bookId;
    private String title;
    private String authorSurname;
    private Instant bookCreatedAt;
    private LocalDateTime notificationReceivedAt;
    private String status;
    private String message;

    public Notification(BookCreatedEvent event) {
        this.bookId = event.getBookId();
        this.title = event.getTitle();
        this.authorSurname = event.getAuthorSurname();
        this.bookCreatedAt = event.getCreatedAt();
        this.notificationReceivedAt = LocalDateTime.now();
        this.status = "CREATED";
        this.message = String.format("Книга '%s' автора %s создана", 
            event.getTitle(), event.getAuthorSurname());
    }
}