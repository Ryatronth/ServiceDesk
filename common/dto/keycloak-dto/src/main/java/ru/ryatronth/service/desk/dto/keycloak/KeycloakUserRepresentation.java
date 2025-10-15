package ru.ryatronth.service.desk.dto.keycloak;

import java.util.UUID;

public record KeycloakUserRepresentation(
    UUID id,
    String username,
    String email,
    String firstName,
    String lastName,
    String patronymic,
    String city,
    String address,
    String workplace,
    Boolean enabled
) {

}
