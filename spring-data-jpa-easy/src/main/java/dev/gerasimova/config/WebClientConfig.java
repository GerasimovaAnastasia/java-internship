package dev.gerasimova.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
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
}
