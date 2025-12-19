package dev.gerasimova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Eureka Server - сервис обнаружения для микросервисов книжного магазина.
 * Запускается на порту 8761 и предоставляет Dashboard для мониторинга
 * зарегистрированных сервисов.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}