package dev.gerasimova.controller;

import dev.gerasimova.service.ServiceValidation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для тестирования.
 */
@RestController
public class TestController {

    /**
     * Контроллер для тестирования корректной работы библиотеки guava.
     *
     * @see ServiceValidation#validationGuava()
     */
    @GetMapping("/test/guava")
    public String testGuava() {
        return ServiceValidation.validationGuava();
    }
    /**
     * Контроллер для вывода версии библиотеки Jackson.
     *
     * @see ServiceValidation#validationVersionForJacksonLib()
     */
    @GetMapping("/test/jackson")
    public String testJackson() throws Exception {
        return ServiceValidation.validationVersionForJacksonLib();
    }
}
