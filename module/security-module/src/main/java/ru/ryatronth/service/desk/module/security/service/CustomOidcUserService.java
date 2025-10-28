package ru.ryatronth.service.desk.module.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.data.persona.model.user.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            UUID keycloakId = UUID.fromString(oidcUser.getSubject());

            userRepository.findById(keycloakId)
                .map(existing -> {
                    existing.setEmail(oidcUser.getEmail());
                    existing.setFirstName(oidcUser.getGivenName());
                    existing.setLastName(oidcUser.getFamilyName());
                    existing.setPatronymic(oidcUser.getClaimAsString("patronymic"));
                    existing.setAddress(oidcUser.getClaimAsString("address"));
                    existing.setWorkplace(oidcUser.getClaimAsString("workplace"));
                    return userRepository.save(existing);
                })
                .orElseGet(() -> {
                    User newUser = User.builder()
                        .id(keycloakId)
                        .email(oidcUser.getEmail())
                        .firstName(oidcUser.getGivenName())
                        .lastName(oidcUser.getFamilyName())
                        .patronymic(oidcUser.getClaimAsString("patronymic"))
                        .address(oidcUser.getClaimAsMap("address").values().toString())
                        .workplace(oidcUser.getClaimAsString("workplace"))
                        .enabled(true)
                        .build();
                    return userRepository.save(newUser);
                });

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении пользователя", e);
        }

        return oidcUser;
    }
}
