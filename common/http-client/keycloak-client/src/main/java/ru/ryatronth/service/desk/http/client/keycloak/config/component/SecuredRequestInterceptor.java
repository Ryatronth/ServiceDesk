package ru.ryatronth.service.desk.http.client.keycloak.config.component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.http.client.keycloak.api.KeycloakTokenService;

@RequiredArgsConstructor
public class SecuredRequestInterceptor implements RequestInterceptor {

    private final KeycloakTokenService keycloakTokenService;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer " + keycloakTokenService.getClientCredentialsToken());
    }

}
