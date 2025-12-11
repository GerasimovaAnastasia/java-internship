package dev.gerasimova.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/**
 * Конфигурационный класс Spring для настройки бинов инфраструктуры.
 * Определяет конфигурацию компонентов, необходимых для работы REST-клиента
 * и взаимодействия с внешними API.
 */
@Configuration
public class RestTemplateConfig {
    /**
     * Создает и настраивает бин RestTemplate для выполнения HTTP-запросов.
     * RestTemplate используется для взаимодействия с внешними REST API сервисами.
     *
     * @return настроенный экземпляр RestTemplate
     * @see RestTemplate
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
