package ru.ryatronth.service.desk.module.security.mapper;

import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeycloakUserMapper {

    User toUser(KeycloakUserRepresentation keycloakUser);

}
