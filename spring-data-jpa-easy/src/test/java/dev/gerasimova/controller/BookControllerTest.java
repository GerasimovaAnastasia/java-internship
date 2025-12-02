package dev.gerasimova.controller;

import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.service.BookService;
import dev.gerasimova.service.UserService;
import dev.gerasimova.utils.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
/**
 * Слайс-тест для контроллера BookController с использованием @WebMvcTest.
 * Тестирует веб-слой Spring MVC в изоляции от сервисов и базы данных.
 * Для обхода Spring Security используется фиктивный пользователь (@WithMockUser).
 */
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookService bookService;
    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;
    @MockitoBean
    private UserService userService;
    /**
     * Тестирует получение книги по ID.
     * Проверяет корректность HTTP-ответа: статус 200 и структуру JSON.
     * Сервис мокируется для возврата подготовленного DTO.
     *
     * @throws Exception если выполнение HTTP-запроса завершится ошибкой
     */
    @Test
    @WithMockUser
    void getBookById() throws Exception {
        BookResponseDto responseDto = new BookResponseDto("Test Book", "Surname",400.0, 2013);
        when(bookService.getBookById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/books/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.authorSurname").value("Surname"))
                .andExpect(jsonPath("$.price").value(400.0))
                .andExpect(jsonPath("$.yearRelease").value(2013));

        verify(bookService, times(1)).getBookById(1L);
    }
}
