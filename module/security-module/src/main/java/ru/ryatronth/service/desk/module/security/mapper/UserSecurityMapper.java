package ru.ryatronth.service.desk.module.security.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.oauth2.jwt.Jwt;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.dto.persona.UserDto;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface UserSecurityMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default void updateUserFromJwt(Jwt jwt, @MappingTarget User user) {
        if (jwt == null) {
            return;
        }

        if (jwt.getClaimAsString("email") != null) {
            user.setEmail(jwt.getClaimAsString("email"));
        }

        if (jwt.getClaimAsString("given_name") != null) {
            user.setFirstName(jwt.getClaimAsString("given_name"));
        }

        if (jwt.getClaimAsString("family_name") != null) {
            user.setLastName(jwt.getClaimAsString("family_name"));
        }

        if (jwt.getClaimAsString("patronymic") != null) {
            user.setPatronymic(jwt.getClaimAsString("patronymic"));
        }

        if (jwt.getClaimAsString("address") != null) {
            user.setAddress(jwt.getClaimAsString("address"));
        }

        if (jwt.getClaimAsString("workplace") != null) {
            user.setWorkplace(jwt.getClaimAsString("workplace"));
        }

        List<String> roles = extractRoles(jwt);
        if (roles != null && !roles.isEmpty()) {
            user.setRoles(roles);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<String> extractRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess == null) {
            return List.of();
        }
        Object roles = realmAccess.get("roles");
        if (roles instanceof List<?> list) {
            return list.stream().map(Object::toString).toList();
        }
        return List.of();
    }

}
