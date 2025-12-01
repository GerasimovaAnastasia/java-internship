package dev.gerasimova.mapper;

import dev.gerasimova.dto.CreateUserDto;
import dev.gerasimova.dto.CreateUserWithRoleDto;
import dev.gerasimova.dto.UserResponseDto;
import dev.gerasimova.model.User;
import dev.gerasimova.model.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Преобразует DTO создания аккаунта пользователя в сущность пользователя.
     * Включает хэш пароля, роль пользователя.
     *
     * @param dto DTO с данными для создания аккаунта
     * @return сущность пользователя
     */
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    User toUser(CreateUserDto dto, String password, UserRole role);
    /**
     * Преобразует сущность пользователя в DTO для ответа.
     * Включает логин пользователя.
     *
     * @param user сущность пользователя для преобразования
     * @return UserResponseDto с логином для ответа пользователю
     */
    @Mapping(target = "username", source = "user.username")
    UserResponseDto toUserResponseDto(User user);
    /**
     * Преобразует DTO создания аккаунта пользователя в сущность пользователя.
     * Включает хэш пароля, роль пользователя.
     *
     * @param dto DTO с данными для создания аккаунта
     * @return сущность пользователя
     */
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    User toUser(CreateUserWithRoleDto dto, String password, UserRole role);
}
