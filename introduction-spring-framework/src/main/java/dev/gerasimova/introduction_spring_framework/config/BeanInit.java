package dev.gerasimova.introduction_spring_framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс-конфигурация для создания бинов
 *
 * @see ObjectMapper
 */
@Configuration
public class BeanInit {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
