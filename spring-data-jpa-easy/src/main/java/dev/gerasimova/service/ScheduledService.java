package dev.gerasimova.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Сервис для выполнения задач по расписанию.
 * Содержит метод, аннотированный @Scheduled для автоматического выполнения в заданное время.
 *
 * @see org.springframework.scheduling.annotation.Scheduled
 */
@Slf4j
@Service
public class ScheduledService {
    @Scheduled(cron = "0 * * * * ?")
    public void runScheduledTask() {
        log.info("Running scheduled task...");
    }
}
