package dev.gerasimova.config;

import com.fasterxml.jackson.core.JsonParseException;
import dev.gerasimova.dto.BookCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaDlqConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public NewTopic dlqTopic() {
        log.info("Создаю DLQ топик: book_events_dlq");
        return new NewTopic("book_events_dlq", 1, (short) 1);
    }
    @Bean
    public ConsumerFactory<String, BookCreatedEvent> bookEventConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "notification_group");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(BookCreatedEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookCreatedEvent> dlqKafkaListenerContainerFactory
            (KafkaOperations<String, BookCreatedEvent> dlqTemplate) {
        log.info("Создаем dlqKafkaListenerContainerFactory...");

        var recoverer = new DeadLetterPublishingRecoverer(dlqTemplate, (rec, ex) ->
                new TopicPartition("book_events_dlq", rec.partition())
        );
        var errorHandler = new DefaultErrorHandler(recoverer, new FixedBackOff(0, 2));
        errorHandler.addNotRetryableExceptions(JsonParseException.class);

        ConcurrentKafkaListenerContainerFactory<String, BookCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bookEventConsumerFactory());
        factory.setCommonErrorHandler(errorHandler);
        factory.setBatchListener(false);
        factory.getContainerProperties().setObservationEnabled(true);
        return factory;
    }
}
