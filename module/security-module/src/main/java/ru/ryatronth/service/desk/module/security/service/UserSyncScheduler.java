package ru.ryatronth.service.desk.module.security.service;

import jakarta.annotation.PostConstruct;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.data.persona.model.user.UserRepository;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;
import ru.ryatronth.service.desk.module.security.mapper.KeycloakUserMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSyncScheduler {

    private static final int PAGE_SIZE = 50;

    private final UserRepository userRepository;

    private final KeycloakUserService keycloakUserService;

    private final KeycloakUserMapper keycloakUserMapper;

    @PostConstruct
    public void init() {
        syncUsers();
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void syncUsers() {
        log.info("Start user sync");

        int currentPage = 0;
        while (true) {
            Page<User> page = userRepository.findAll(PageRequest.of(currentPage, PAGE_SIZE));
            if (page.isEmpty()) {
                break;
            }

            for (User user : page.getContent()) {
                try {
                    userRepository.save(tryToUpdate(user));
                } catch (Exception e) {
                    log.error("Error updating the user [{}]", user.getId(), e);
                }
            }

            currentPage++;
        }

        log.info("End user sync");
    }

    private User tryToUpdate(User user) {
        Optional<KeycloakUserRepresentation> representation = Optional.empty();
        if (user.getId() != null) {
            representation = keycloakUserService.getKeycloakUser(user.getId().toString());
        }

        if (representation.isEmpty() && user.getEmail() != null) {
            representation = keycloakUserService.getKeycloakUserByEmail(user.getEmail());
        }

        return representation.map(keycloakUserMapper::toUser).orElseGet(() -> {
            log.warn("No user found in Keycloak [{}]", user.getId());
            user.setEnabled(false);
            return user;
        });
    }

}
