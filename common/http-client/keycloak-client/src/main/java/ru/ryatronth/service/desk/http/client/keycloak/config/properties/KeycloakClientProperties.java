package ru.ryatronth.service.desk.http.client.keycloak.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http-client.keycloak")
public record KeycloakClientProperties(
    String host,
    String realm,
    String clientId,
    String clientSecret
) {

}
