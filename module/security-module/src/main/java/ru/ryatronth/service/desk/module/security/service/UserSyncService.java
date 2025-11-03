package ru.ryatronth.service.desk.module.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.data.persona.model.user.UserRepository;
import ru.ryatronth.service.desk.dto.persona.UserDto;
import ru.ryatronth.service.desk.module.security.mapper.UserSecurityMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSyncService {

    private final UserRepository userRepository;

    private final UserSecurityMapper userSecurityMapper;

    public UserDto syncUserFromJwt(Jwt jwt) {
        UUID keycloakId = UUID.fromString(jwt.getSubject());

        return userSecurityMapper.toDto(userRepository.findById(keycloakId).map(existing -> {
            userSecurityMapper.updateUserFromJwt(jwt, existing);
            return userRepository.save(existing);
        }).orElseGet(() -> {
            User newUser = new User();
            newUser.setId(keycloakId);
            newUser.setEnabled(true);
            userSecurityMapper.updateUserFromJwt(jwt, newUser);
            return userRepository.save(newUser);
        }));
    }

}
