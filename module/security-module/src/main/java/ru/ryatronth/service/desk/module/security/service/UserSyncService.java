package ru.ryatronth.service.desk.module.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.data.persona.model.user.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSyncService {

    private final UserRepository userRepository;

    public void syncUserFromJwt(Jwt jwt) {
        UUID keycloakId = UUID.fromString(jwt.getSubject());

        String email = jwt.getClaimAsString("email");
        String firstName = jwt.getClaimAsString("given_name");
        String lastName = jwt.getClaimAsString("family_name");
        String patronymic = jwt.getClaimAsString("patronymic");
        String address = jwt.getClaimAsString("address");
        String workplace = jwt.getClaimAsString("workplace");

        List<String> roles = extractRoles(jwt);

        userRepository.findById(keycloakId).map(existing -> {
            existing.setEmail(email);
            existing.setFirstName(firstName);
            existing.setLastName(lastName);
            existing.setPatronymic(patronymic);
            existing.setAddress(address);
            existing.setWorkplace(workplace);
            existing.setRoles(roles);
            return userRepository.save(existing);
        }).orElseGet(() -> {
            User newUser = User.builder()
                               .id(keycloakId)
                               .email(email)
                               .firstName(firstName)
                               .lastName(lastName)
                               .patronymic(patronymic)
                               .address(address)
                               .workplace(workplace)
                               .enabled(true)
                               .roles(roles)
                               .build();
            return userRepository.save(newUser);
        });
    }

    @SuppressWarnings("unchecked")
    private List<String> extractRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess == null) return List.of();
        Object roles = realmAccess.get("roles");
        if (roles instanceof List<?> list) {
            return list.stream().map(Object::toString).toList();
        }
        return List.of();
    }

}
