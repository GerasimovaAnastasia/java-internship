package dev.gerasimova.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Конфигурационный класс для настройки параметров OpenAPI Swagger
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library API")
                        .version("1.0")
                        .description("API для управления библиотекой с JWT аутентификацией"))
                .addSecurityItem(new SecurityRequirement().addList("JWT"))

                .addServersItem(new Server()
                        .url("http://localhost:8080/book-service")
                        .description("Через API Gateway (рекомендуется)"))
                .addServersItem(new Server()
                        .url("/")
                        .description("Прямой доступ к BookService"))

                .components(new Components()
                        .addSecuritySchemes("JWT", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .description("Введите: Bearer <ваш_токен>")));
    }
}
