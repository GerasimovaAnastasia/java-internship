package dev.gerasimova;

import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.dto.LoginUserDto;
import dev.gerasimova.dto.CreateUserWithRoleDto;
import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import dev.gerasimova.model.UserRole;
import dev.gerasimova.service.AuthService;
import dev.gerasimova.service.AuthorService;
import dev.gerasimova.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * End-to-End тест приложения с использованием реальной PostgreSQL в Docker.
 * Testcontainers запускает контейнер с БД на время тестов.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Testcontainers
class ApplicationE2ETest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("book_test_db")
            .withUsername("test")
            .withPassword("test");
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthService authService;
    @LocalServerPort
    private int port;
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        registry.add("spring.flyway.url", postgres::getJdbcUrl);
        registry.add("spring.flyway.user", postgres::getUsername);
        registry.add("spring.flyway.password", postgres::getPassword);
    }
    @BeforeEach
    void setUp() {
        CreateUserWithRoleDto createUserWithRoleDto = new CreateUserWithRoleDto("admin", "admin", UserRole.ROLE_ADMIN);
        authService.createUser(createUserWithRoleDto);
        authorService.saveAuthor(new Author("Name", "Surname"));
    }
    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/books";
    }
    /**
     * Тестирует полный цикл создания и получения книги.
     * 1. Создает книгу через POST запрос
     * 2. Проверяет, что книга сохранена в БД
     * 3. Получает книгу через GET запрос
     * 4. Проверяет данные
     */
    @Test
    void createAndGetBookShouldWorkEndToEnd() {
        LoginUserDto loginUserDto = new LoginUserDto("admin", "admin");
        String jwtToken = authService.login(loginUserDto);
        String titleBook = "E2E Test Book";

        CreateBookDto bookDto = new CreateBookDto(
                titleBook,
                1L,
                400.0,
                2013
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);
        HttpEntity<CreateBookDto> request = new HttpEntity<>(bookDto, headers);

        ResponseEntity<BookResponseDto> response = restTemplate.postForEntity(
                getBaseUrl(),
                request,
                BookResponseDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().title()).isEqualTo(titleBook);

        Pageable pageable = PageRequest.of(0,10, Sort.by("title"));
        List<Book> booksInDb = bookService.getAllBooks(pageable).toList();
        assertThat(booksInDb).isNotEmpty();

        Book savedBook = booksInDb.stream()
                .filter(b -> titleBook.equals(b.getTitle()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Книга не найдена в БД!"));

        assertThat(savedBook.getPrice()).isEqualTo(400.0);
        assertThat(savedBook.getYearRelease()).isEqualTo(2013);
        assertThat(savedBook.getAuthor().getSurname()).isEqualTo("Surname");

        Long bookId = savedBook.getId();

        HttpEntity<Void> getRequest = new HttpEntity<>(headers);
        ResponseEntity<BookResponseDto> getResponse = restTemplate.exchange(
                getBaseUrl() + "/" + bookId,
                HttpMethod.GET,
                getRequest,
                BookResponseDto.class
        );

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        BookResponseDto retrievedBook = getResponse.getBody();
        assertThat(retrievedBook.title()).isEqualTo(titleBook);
    }
}
