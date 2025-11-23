package dev.gerasimova.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Сервис для бизнес-логики асинхронных задач.
 */
@Service
@Slf4j
public class AsyncService {
    /**
     * Метод, имитирующий работу тяжелой задачи,
     * выполняющийся в фоновом потоке через @Async.
     */
    @Async
    public void asyncMethod() {
        try {
            log.info("Асинхронная задача началась!");
            Thread.sleep(5000);
            log.info("Асинхронная задача завершена!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
