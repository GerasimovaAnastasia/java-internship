package dev.gerasimova.service;

import dev.gerasimova.dto.BookNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationServiceClient {

    @PostMapping("/notify")
    String sendNotification(@RequestBody BookNotificationRequest request);
}
