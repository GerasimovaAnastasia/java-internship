package dev.gerasimova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server - сервис обнаружения для микросервисов книжного магазина.
 * Запускается на порту 8761 и предоставляет Dashboard для мониторинга
 * зарегистрированных сервисов.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}