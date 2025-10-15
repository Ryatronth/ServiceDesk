package ru.ryatronth.service.desk.http.client.keycloak.config;

import feign.RequestInterceptor;
import ru.ryatronth.service.desk.http.client.keycloak.api.KeycloakTokenService;
import ru.ryatronth.service.desk.http.client.keycloak.config.component.SecuredRequestInterceptor;

import org.springframework.context.annotation.Bean;

public class SecuredKeycloakClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor(KeycloakTokenService keycloakTokenService) {
        return new SecuredRequestInterceptor(keycloakTokenService);
    }

}
