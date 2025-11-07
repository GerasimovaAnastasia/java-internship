package dev.gerasimova.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import dev.gerasimova.model.TestData;

/**
 * Сервисный класс для тестирования библиотек Jackson и Guava.
 */
public class ServiceValidation {

    /**
     * Метод для тестирования корректной работы библиотеки guava.
     */
    public static String validationGuava() {
        String str = "Hello!";
        if (!Strings.isNullOrEmpty(str)) {
            return "Guava работает! Строка не пустая!";
        }
        return "Ошибка Guava";
    }
    /**
     * Метод для вывода версии библиотеки Jackson.
     */
    public static String validationVersionForJacksonLib() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String version = mapper.version().toString();
        TestData data = new TestData("Test", 25);
        String json = mapper.writeValueAsString(data);

        return String.format("Jackson %s работает: %s", version, json);
    }
}
