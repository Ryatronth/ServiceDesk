package ru.ryatronth.service.desk.dto.keycloak;

public record KeycloakTokens(
    String accessToken,
    long expiresAt
) {

    public boolean isExpired() {
        return System.currentTimeMillis() >= expiresAt;
    }

}
