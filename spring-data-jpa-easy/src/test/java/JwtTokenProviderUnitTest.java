import dev.gerasimova.utils.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * Unit-тесты для JwtTokenProvider без Spring контекста.
 * Проверяет базовую функциональность генерации и валидации JWT токенов.
 *
 * @see JwtTokenProvider
 */
class JwtTokenProviderUnitTest {

    private JwtTokenProvider jwtTokenProvider;
    /**
     * Инициализирует JwtTokenProvider перед каждым тестом.
     * Использует тестовый секретный ключ и время жизни 15 минут.
     */
    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(
                "testSecretKey1234567890123456789012",
                900000
        );
    }
    /**
     * Проверяет что метод generateToken создает непустой токен.
     */
    @Test
    void testGenerateToken() {
        String username = "testuser";

        String token = jwtTokenProvider.generateToken(username);

        assertNotNull(token, "Токен не должен быть null");
        assertTrue(token.length() > 10, "Токен должен иметь длину > 10 символов");
    }
    /**
     * Проверяет что невалидный токен корректно определяется как недействительный.
     */
    @Test
    void testInvalidToken() {
        String invalidToken = "invalid.token.here";

        assertFalse(jwtTokenProvider.validateToken(invalidToken),
                "Невалидный токен должен возвращать false");
    }
    /**
     * Проверяет что имя пользователя корректно извлекается из токена.
     */
    @Test
    void testGetUsernameFromToken() {
        String username = "testuser";
        String token = jwtTokenProvider.generateToken(username);

        String extractedUsername = jwtTokenProvider.getUsernameFromToken(token);

        assertEquals(username, extractedUsername,
                "Извлеченное имя пользователя должно совпадать с оригиналом");
    }
}
