package ru.ryatronth.service.desk.http.client.keycloak.api;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakTokenResponse;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakTokens;
import ru.ryatronth.service.desk.http.client.keycloak.KeycloakAuthClient;
import ru.ryatronth.service.desk.http.client.keycloak.config.properties.KeycloakClientProperties;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakTokenService {

    private final KeycloakClientProperties properties;

    private final KeycloakAuthClient keycloakAuthClient;

    private KeycloakTokens tokens;

    /**
     * Получение access token через Client Credentials Flow
     */
    public String getClientCredentialsToken() {
        if (tokens == null || tokens.isExpired()) {
            tokens = getNewClientCredentialsToken();
        }

        return tokens.accessToken();
    }

    private KeycloakTokens getNewClientCredentialsToken() {
        log.info("Запрос нового клиентского токена Keycloak");

        try {
            Map<String, String> form = new LinkedHashMap<>();
            form.put("grant_type", "client_credentials");
            form.put("client_id", properties.clientId());
            form.put("client_secret", properties.clientSecret());
            form.put("scope", "openid profile email");

            KeycloakTokenResponse
                response = keycloakAuthClient.getClientCredentialsToken(properties.realm(), form);
            return new KeycloakTokens(response.accessToken(), System.currentTimeMillis() + response.expiresIn() * 1000L);
        }  catch (Exception e) {
            log.error("Ошибка при запросе нового токена Keycloak", e);
            throw e;
        }
    }

}
