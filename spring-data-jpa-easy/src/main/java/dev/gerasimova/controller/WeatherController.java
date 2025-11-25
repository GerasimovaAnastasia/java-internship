package dev.gerasimova.controller;

import dev.gerasimova.service.WttrWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;
/**
 * REST контроллер для предоставления данных о погоде.
 * Обеспечивает эндпоинты для получения текущей погоды в различных форматах.
 * Использует внешний сервис wttr.in для получения актуальных метеорологических данных.
 *
 * @see WttrWeatherService
 */
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WttrWeatherService weatherService;
    /**
     * Возвращает информацию о погоде в указанном городе в формате JSON.
     * Включает температуру, влажность, скорость ветра, описание погоды и другие метеопараметры.
     *
     * @param city название города для запроса погоды (обязательный параметр)
     * @return ResponseEntity с картой данных о погоде в формате ключ-значение
     * @throws RuntimeException если внешний сервис недоступен или город не найден
     */
    @Operation(summary = "Получить детальную погоду в JSON")
    @GetMapping("/{city}")
    public ResponseEntity<Map<String, Object>> getDetailedWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getWeatherJson(city));
    }
    /**
     * Возвращает текстовое представление погоды в асинхронном режиме.
     * Метод не блокирует поток выполнения пока ожидает ответ от внешнего API.
     *
     * @param city название города
     * @return Mono с текстовым представлением погоды
     */
    @Operation(summary = "Получить погоду в текстовом формате (асинхронно)")
    @GetMapping("/{city}/text")
    public Mono<String> getWeatherText(@PathVariable String city) {
        return weatherService.getWeatherText(city);
    }

}
