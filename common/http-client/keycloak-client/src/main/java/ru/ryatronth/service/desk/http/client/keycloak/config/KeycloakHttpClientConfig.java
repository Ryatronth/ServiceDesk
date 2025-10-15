package ru.ryatronth.service.desk.http.client.keycloak.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan("ru.ryatronth.service.desk.http.client.keycloak")
@ComponentScan("ru.ryatronth.service.desk.http.client.keycloak")
@EnableFeignClients("ru.ryatronth.service.desk.http.client.keycloak")
public class KeycloakHttpClientConfig {
}
