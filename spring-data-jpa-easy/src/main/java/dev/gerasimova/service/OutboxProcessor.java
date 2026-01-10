package dev.gerasimova.service;

import dev.gerasimova.model.OutboxEvent;
import dev.gerasimova.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxProcessor {
    
    private final OutboxEventRepository outboxEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Метод раз в 5 сек проверяет, есть ли неопубликованные сообщения в таблице outbox_events.
     * Если есть - публикует их в Кафка.
     * После успешной публикации в Кафка помечает сообщения обработанными.
     */
    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processOutbox() {

        List<OutboxEvent> unpublishedEvents =
            outboxEventRepository.findUnpublishedEvents();
        
        if (unpublishedEvents.isEmpty()) {
            log.debug("Нет неопубликованных событий");
            return;
        }
        
        log.info("Найдено {} неопубликованных событий", unpublishedEvents.size());
        
        for (OutboxEvent event : unpublishedEvents) {
            try {
                kafkaTemplate.send("book_events", event.getEventData())
                        .get(5, java.util.concurrent.TimeUnit.SECONDS);

                outboxEventRepository.markAsPublished(event.getId());
                log.info("Событие {} опубликовано", event.getId());
                
            } catch (Exception e) {
                log.error("Ошибка при публикации события {}", event.getId(), e);

            }
        }
    }
}