package dev.gerasimova.controller;

import dev.gerasimova.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для тестирования асинхронного метода.
 *
 * @see AsyncService
 */

@RestController
@RequiredArgsConstructor
public class AsyncController {
    private final AsyncService asyncService;

    /**
     * Метод, демонстрирующий асинхронность.
     * Суть endpoint-а в том, что ответ пользователь получит сразу после отправки запроса.
     * Задача будет выполняться в фоновом потоке какое-то время через @Async,
     * пользователь же получит ответ быстро, а результат операции позднее.
     *
     * @return String подтверждение запуска фоновой задачи.
     */
    @GetMapping("/asyncTest")
    public ResponseEntity<String> asyncTest() {
        asyncService.asyncMethod();
        return ResponseEntity.ok("Задача запущена в фоне! Проверь логи через 5 секунд.");
    }
}
