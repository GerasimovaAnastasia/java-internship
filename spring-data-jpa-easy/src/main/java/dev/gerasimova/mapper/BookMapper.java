package dev.gerasimova.mapper;

import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.dto.CreateBookWithAuthorDto;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Компонент для преобразования между сущностями и DTO.
 * Обеспечивает маппинг данных между сервисным слоем и API.
 *
 * @see Book
 * @see BookResponseDto
 * @see CreateBookDto
 * @see CreateBookWithAuthorDto
 */
@Mapper(componentModel = "spring")
public interface BookMapper {

    /**
     * Преобразует сущность книги в DTO для ответа.
     * Включает основные данные книги и фамилию автора.
     *
     * @param book сущность книги для преобразования
     * @return BookResponseDto с данными книги для ответа пользователю
     * @throws NullPointerException если book или author равен null
     */
    @Mapping(source = "author.surname", target = "authorSurname")
    BookResponseDto toBookResponseDto(Book book);
    /**
     * Преобразует DTO создания книги в сущность книги.
     * Не включает автора - он должен быть установлен отдельно.
     *
     * @param dto DTO с данными для создания книги
     * @return сущность книги без установленного автора
     * @see Book#setAuthor(Author)
     */
    @Mapping(source = "dto.yearRelease", target = "date")
    Book toBook(CreateBookDto dto);
}
