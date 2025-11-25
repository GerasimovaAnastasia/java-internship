package dev.gerasimova.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
/**
 * Сервис для получения данных о погоде из внешнего API wttr.in.
 * Обеспечивает взаимодействие с метеорологическим сервисом через REST API.
 *
 * @see RestTemplate
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WttrWeatherService {
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    @Value("${demoUrl}")
    private String API_URL;
    /**
     * Получает данные о погоде для указанного города в формате JSON.
     * Выполняет HTTP-запрос к внешнему API и возвращает структурированные метеоданные.
     *
     * @param city название города для запроса погоды
     * @return карта с метеорологическими параметрами города
     *
     * @see Map
     */
    public Map<String, Object> getWeatherJson(String city) {
        String url = API_URL + city + "?format=j1";
        System.out.println(url);
        return restTemplate.getForObject(url, Map.class);
    }
    /**
     * Получает данные о погоде в асинхронном режиме.
     * Возвращает Mono<String> который содержит результат или ошибку.
     *
     * @param city название города для запроса погоды
     * @return Mono с текстовым представлением погоды
     */
    public Mono<String> getWeatherText(String city) {
        return webClient.get()
                .uri("/{city}?format=3", city)
                .retrieve()
                .bodyToMono(String.class);
    }
}
