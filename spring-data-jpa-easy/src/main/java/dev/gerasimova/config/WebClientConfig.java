package dev.gerasimova.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Конфигурационный класс для настройки реактивного WebClient.
 * WebClient - современная асинхронная замена RestTemplate.
 *
 */
@Configuration
public class WebClientConfig {

    /**
     * Создает и настраивает бин WebClient для асинхронных HTTP-запросов.
     * WebClient обеспечивает неблокирующее выполнение запросов и лучше подходит
     * для высоконагруженных приложений по сравнению с RestTemplate.
     *
     * @return настроенный экземпляр WebClient
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://wttr.in")
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring WebClient")
                .build();
    }
    /**
     * Создает и настраивает бин WebClient для асинхронных HTTP-запросов.
     * Бин настраивает запросы для NotificationService
     *
     * @return настроенный экземпляр WebClient
     */
    @Bean("notificationWebClient")
    public WebClient notificationWebClient() {
        return WebClient.builder()
                .baseUrl("http://notification-service:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
