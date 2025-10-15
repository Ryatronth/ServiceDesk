package ru.ryatronth.service.desk.service.user.sync;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;
import ru.ryatronth.service.desk.dto.persona.UserDto;
import ru.ryatronth.service.desk.module.persona.UserService;
import ru.ryatronth.service.desk.module.security.KeycloakUserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSyncScheduler {

    private static final int PAGE_SIZE = 50;

    private final UserService userService;

    private final KeycloakUserService keycloakUserService;

    private final KeycloakUserMapper keycloakUserMapper;

    @Scheduled(cron = "0 0 6 * * *")
    public void syncUsers() {
        log.info("Start user sync");

        int currentPage = 0;
        while (true) {
            Page<UserDto> page = userService.getPage(PageRequest.of(currentPage, PAGE_SIZE));
            if (page.isEmpty()) {
                break;
            }

            for (UserDto user : page.getContent()) {
                try {
                    userService.update(user.id(), tryToUpdate(user));
                } catch (Exception e) {
                    log.error("Error updating the user [{}]", user.id(), e);
                }
            }

            currentPage++;
        }

        log.info("End user sync");
    }

    private UserDto tryToUpdate(UserDto user) {
        Optional<KeycloakUserRepresentation> representation = Optional.empty();
        if (user.id() != null) {
            representation = keycloakUserService.getKeycloakUser(user.id().toString());
        }

        if (representation.isEmpty() && user.email() != null) {
            representation = keycloakUserService.getKeycloakUserByEmail(user.email());
        }

        return representation.map(keycloakUserMapper::toUserDto).orElseGet(() -> {
            log.warn("No user found in Keycloak [{}]", user.id());
            return keycloakUserMapper.toDisabledUserDto(user);
        });
    }

}
