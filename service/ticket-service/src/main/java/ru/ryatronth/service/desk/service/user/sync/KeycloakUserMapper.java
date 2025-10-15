package ru.ryatronth.service.desk.service.user.sync;

import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;
import ru.ryatronth.service.desk.dto.persona.UserDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KeycloakUserMapper {

    UserDto toUserDto(KeycloakUserRepresentation keycloakUser);

    @Mapping(target = "enabled", constant = "false")
    UserDto toDisabledUserDto(UserDto disabledUser);

}
