package ru.ryatronth.service.desk.http.client.keycloak;

import java.util.Map;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakTokenResponse;
import ru.ryatronth.service.desk.http.client.keycloak.config.BaseKeycloakClientConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "keycloakAuthClient", url = "${http-clients.keycloak.host}", configuration = {BaseKeycloakClientConfig.class})
public interface KeycloakAuthClient {

    /**
     * Получение access token через Client Credentials Flow
     */
    @PostMapping(value = "/realms/{realm}/protocol/openid-connect/token",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KeycloakTokenResponse getClientCredentialsToken(@PathVariable("realm") String realm,
                                                    @RequestBody Map<String, ?> form);


}
