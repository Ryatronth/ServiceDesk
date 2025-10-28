package ru.ryatronth.service.desk.module.security.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import ru.ryatronth.service.desk.module.security.service.CustomOidcUserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler,
        CustomOidcUserService customOidcUserService) throws Exception {

        http
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(customOidcUserService) // <-- подставляем наш сервис
                )
            )
            .logout(logout -> logout
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }


    @Bean
    public OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler(
        ClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedLogoutSuccessHandler handler =
            new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        handler.setPostLogoutRedirectUri("{baseUrl}/");
        return handler;
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mapped = new HashSet<>();
            for (GrantedAuthority authority : authorities) {
                if (authority instanceof OidcUserAuthority oidc) {
                    Map<String, Object> claims = oidc.getIdToken().getClaims();
                    Object realmAccess = claims.get("realm_access");
                    if (realmAccess instanceof Map<?, ?> map) {
                        Object roles = map.get("roles");
                        if (roles instanceof Collection<?> col) {
                            for (Object r : col) {
                                mapped.add(new SimpleGrantedAuthority("ROLE_" + r));
                            }
                        }
                    }
                }
            }
            return mapped;
        };
    }

}

