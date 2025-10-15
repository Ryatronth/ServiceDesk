package ru.ryatronth.service.desk.http.client.keycloak;

import java.util.List;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakGroupRepresentation;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakUserRepresentation;
import ru.ryatronth.service.desk.http.client.keycloak.config.BaseKeycloakClientConfig;
import ru.ryatronth.service.desk.http.client.keycloak.config.SecuredKeycloakClientConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "keycloakClient", url = "${http-clients.keycloak.host}", configuration = {BaseKeycloakClientConfig.class, SecuredKeycloakClientConfig.class})
public interface KeycloakClient {

    /**
     * Получение пользователя по ID
     */
    @GetMapping("/admin/realms/{realm}/users/{userId}")
    KeycloakUserRepresentation getUserById(@PathVariable("realm") String realm,
                                           @PathVariable("userId") String userId);

    /**
     * Получение пользователя по email
     */
    @GetMapping("/admin/realms/{realm}/users")
    List<KeycloakUserRepresentation> getUserByEmail(@PathVariable("realm") String realm,
                                                    @RequestParam("email") String email,
                                                    @RequestParam(value = "exact", defaultValue = "true") Boolean exact);

    /**
     * Получение realm ролей пользователя
     */
    @GetMapping("/admin/realms/{realm}/users/{userId}/groups")
    List<KeycloakGroupRepresentation> getUserGroups(@PathVariable("realm") String realm,
                                                    @PathVariable("userId") String userId);

}
