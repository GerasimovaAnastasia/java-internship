package dev.gerasimova.service;

import dev.gerasimova.model.EmailSender;
import dev.gerasimova.model.NotificationSender;
import dev.gerasimova.model.SmsSender;
import dev.gerasimova.model.PushSender;

import java.util.List;

/**
 * Сервисный класс для отправки сообщений.
 *
 * @see NotificationSender
 * @see EmailSender
 * @see SmsSender
 * @see PushSender
 */
public class ServiceSender {

    /**
     * Отправляет заданное сообщение всеми возможными способами.
     *
     * @param senderList - список отправителей сообщения.
     */

    public static void messageHandling(List<NotificationSender> senderList) {
        for (NotificationSender sender : senderList) {
            sender.send("Привет!");
        }
    }
}
