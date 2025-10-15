package ru.ryatronth.service.desk.module.security;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakGroupRepresentation;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;
import ru.ryatronth.service.desk.http.client.keycloak.KeycloakClient;
import ru.ryatronth.service.desk.http.client.keycloak.config.properties.KeycloakClientProperties;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    private final KeycloakClient keycloakClient;

    private final KeycloakClientProperties keycloakClientProperties;

    public Optional<KeycloakUserRepresentation> getKeycloakUser(String id) {
        return Optional.ofNullable(keycloakClient.getUserById(keycloakClientProperties.realm(), id));
    }

    public Optional<KeycloakUserRepresentation> getKeycloakUserByEmail(String email) {
        List<KeycloakUserRepresentation> users = keycloakClient.getUserByEmail(keycloakClientProperties.realm(), email, true);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.getFirst());
    }

    public Optional<List<KeycloakGroupRepresentation>> getUserGroups(String userId) {
        List<KeycloakGroupRepresentation> groups = keycloakClient.getUserGroups(keycloakClientProperties.realm(), userId);
        if (groups.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(groups);
    }

}
