package ru.ryatronth.service.desk.module.security.mapper;

import java.util.List;
import java.util.Map;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakGroupRepresentation;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.oauth2.jwt.Jwt;

@Mapper(componentModel = "spring")
public interface UserSecurityMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default void updateUserFromKeycloak(KeycloakUserRepresentation kcUser,
                                        List<KeycloakGroupRepresentation> groups,
                                        @MappingTarget User user) {
        if (kcUser == null) {
            return;
        }

        user.setEnabled(kcUser.enabled() == null || kcUser.enabled());
        user.setEmail(kcUser.email());
        user.setFirstName(kcUser.firstName());
        user.setLastName(kcUser.lastName());

        Map<String, List<String>> attrs = kcUser.attributes();
        if (attrs != null) {
            setIfNotNull(user::setPatronymic, getAttr(attrs, "patronymic"));
            setIfNotNull(user::setBranch, getAttr(attrs, "branch"));
            setIfNotNull(user::setWorkplace, getAttr(attrs, "workplace"));
        }

        if (groups != null && !groups.isEmpty()) {
            groups.stream()
                    .filter(UserSecurityMapper::isBranchGroup)
                    .map(KeycloakGroupRepresentation::name)
                    .findFirst().ifPresent(user::setBranch);

            List<String> serviceDeskRoles = groups.stream()
                    .filter(UserSecurityMapper::isServiceDeskGroup)
                    .map(KeycloakGroupRepresentation::name)
                    .toList();

            if (!serviceDeskRoles.isEmpty()) {
                user.setRoles(serviceDeskRoles);
            }

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

    private static String getAttr(Map<String, List<String>> attrs, String key) {
        List<String> values = attrs.get(key);
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

    private static boolean isBranchGroup(KeycloakGroupRepresentation group) {
        String path = group.path();
        return path != null && path.startsWith("/branch/");
    }

    private static boolean isServiceDeskGroup(KeycloakGroupRepresentation group) {
        String path = group.path();
        return path != null && path.startsWith("/service_desk/");
    }

    @FunctionalInterface
    interface Setter<T> {
        void accept(T value);
    }

    private static <T> void setIfNotNull(Setter<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
