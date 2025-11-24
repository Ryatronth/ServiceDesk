package ru.ryatronth.service.desk.dto.keycloak;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record KeycloakUserRepresentation(
    UUID id,
    String username,
    String email,
    String firstName,
    String lastName,
    Boolean enabled,
    Map<String, List<String>> attributes
) {

}
