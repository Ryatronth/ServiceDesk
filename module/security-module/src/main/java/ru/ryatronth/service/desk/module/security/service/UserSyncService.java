package ru.ryatronth.service.desk.module.security.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.data.persona.model.user.UserRepository;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakGroupRepresentation;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;
import ru.ryatronth.service.desk.http.client.keycloak.KeycloakClient;
import ru.ryatronth.service.desk.http.client.keycloak.config.properties.KeycloakClientProperties;
import ru.ryatronth.service.desk.module.security.mapper.UserSecurityMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSyncService {

    private static final int PAGE_SIZE = 100;

    private final UserRepository userRepository;
    private final UserSecurityMapper userSecurityMapper;
    private final KeycloakClient keycloakClient;
    private final KeycloakClientProperties keycloakClientProperties;

    @Transactional
    public void syncAllUsersFromKeycloak() {
        log.info("Start sync users");
        int first = 0;

        while (true) {
            List<KeycloakUserRepresentation> batch =
                    keycloakClient.getUsers(keycloakClientProperties.realm(), first, PAGE_SIZE);

            if (batch == null || batch.isEmpty()) {
                break;
            }

            batch.forEach(this::syncSingleUser);

            first += PAGE_SIZE;
        }
    }

    private void syncSingleUser(KeycloakUserRepresentation kcUser) {
        UUID keycloakId = kcUser.id();

        User user = userRepository.findById(keycloakId)
                .orElseGet(() -> {
                    User u = new User();
                    u.setId(keycloakId);
                    return u;
                });

        List<KeycloakGroupRepresentation> groups =
                keycloakClient.getUserGroups(keycloakClientProperties.realm(), kcUser.id().toString());

        userSecurityMapper.updateUserFromKeycloak(kcUser, groups, user);

        userRepository.save(user);
    }

}
