package dev.gerasimova.dto;

import dev.gerasimova.model.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
/**
 * DTO для отправки сообщений для службы уведомлений NotificationService.
 */
@Schema(description = "DTO для отправки сообщений для службы уведомлений")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreatedEvent {
    private Long bookId;
    private String title;
    private String authorSurname;
    private Instant createdAt;
    public static BookCreatedEvent from(Book book) {
        return new BookCreatedEvent(
            book.getId(),
            book.getTitle(),
            book.getAuthor().getSurname(),
            Instant.now()
        );
    }
}