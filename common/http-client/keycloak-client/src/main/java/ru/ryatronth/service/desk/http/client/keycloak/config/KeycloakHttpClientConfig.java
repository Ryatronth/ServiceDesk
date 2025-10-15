package ru.ryatronth.service.desk.http.client.keycloak.config;

import ru.renue.bot.util.property.YamlPropertySourceFactory;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationPropertiesScan("ru.ryatronth.service.desk.http.client.keycloak")
@ComponentScan("ru.ryatronth.service.desk.http.client.keycloak")
@EnableFeignClients("ru.ryatronth.service.desk.http.client.keycloak")
@PropertySource(value = "classpath:keycloak-client.yml", factory = YamlPropertySourceFactory.class)
public class KeycloakHttpClientConfig {
}
