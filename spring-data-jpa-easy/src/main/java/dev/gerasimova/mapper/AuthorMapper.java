package dev.gerasimova.mapper;

import dev.gerasimova.dto.CreateBookWithAuthorDto;
import dev.gerasimova.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Компонент для преобразования между сущностями и DTO.
 * Обеспечивает маппинг данных между сервисным слоем и API.
 *
 * @see Author
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    /**
     * Преобразует DTO создания книги с автором в сущность автора.
     * Используется когда нужно создать и книгу и автора одновременно.
     *
     * @param dto DTO с данными автора из запроса
     * @return сущность автора для сохранения
     */
    @Mapping(source = "dto.authorName", target = "name")
    @Mapping(source = "dto.authorSurname", target = "surname")
    Author toAuthor(CreateBookWithAuthorDto dto);
}
