import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.mapper.AuthorMapper;
import dev.gerasimova.mapper.BookMapper;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import dev.gerasimova.repository.AuthorRepository;
import dev.gerasimova.repository.BookRepository;
import dev.gerasimova.service.AuthorService;
import dev.gerasimova.service.BookService;
import dev.gerasimova.utils.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * Unit-тесты для BookService.
 *
 * @see BookService
 * @see BookRepository
 * @see Book
 * @see AuthorService
 * @see AuthorRepository
 * @see Author
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private AuthorMapper authorMapper;
    /**
     * Проверяет что метод findById корректно находит книгу по id.
     */
    @Test
    void findByIdTest() {
        Long id = 1L;
        Author author = new Author();
        author.setId(id);
        author.setName("Test Author name");
        author.setSurname("Test Author surname");

        Book testBook = new Book();
        testBook.setId(id);
        testBook.setTitle("Test Book");
        testBook.setPrice(400.0);
        testBook.setYearRelease(2000);
        testBook.setAuthor(author);

        BookResponseDto expectedDto = new BookResponseDto(
                "Test Book", "Test Author surname", 400.0, 2000
        );
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(testBook));
        Mockito.when(bookMapper.toBookResponseDto(testBook)).thenReturn(expectedDto);

        AuthorService authorService = new AuthorService(authorRepository);
        BookService bookService = new BookService(bookRepository, authorService, bookMapper, authorMapper);
        BookResponseDto result = bookService.getBookById(id);

        assertNotNull(result);
        assertEquals("Test Book", result.title());
        assertEquals("Test Author surname", result.authorSurname());
        assertEquals(400.0, result.price());

        Mockito.verify(bookRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookMapper, Mockito.times(1)).toBookResponseDto(testBook);
    }
}
