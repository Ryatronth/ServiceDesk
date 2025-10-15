package ru.ryatronth.service.desk.module.persona;

import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.dto.persona.UserDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    void updateMutable(UserDto userDto, @MappingTarget User user);

    User updateImmutable(UserDto dto, @MappingTarget User entity);

    default User merge(User entity, UserDto dto) {
        if (entity == null) {
            return toEntity(dto);
        }

        return updateImmutable(dto, entity);
    }
}
