package dev.gerasimova;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для тестирования.
 */
@RestController
public class TestController {

    /**
     * Метод для тестирования корректной работы библиотеки guava.
     */
    @GetMapping("/test/guava")
    public String testGuava() {
        String str = "Hello!";
        if (!Strings.isNullOrEmpty(str)) {
            return "Guava работает! Строка не пустая!";
        }
        return "Ошибка Guava";
    }
    /**
     * Метод для вывода версии библиотеки Jackson.
     */
    @GetMapping("/test/jackson")
    public String testJackson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String version = mapper.version().toString();
        TestData data = new TestData("Test", 25);
        String json = mapper.writeValueAsString(data);

        return String.format("Jackson %s работает: %s", version, json);
    }
}
