package ru.ryatronth.service.desk.module.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import ru.ryatronth.service.desk.module.security.service.UserSyncService;

@RestController
@RequiredArgsConstructor
public class UserSyncController implements UserSyncApi{

    private final UserSyncService userSyncService;

    @Override
    public void syncUser(@AuthenticationPrincipal Jwt jwt) {
        userSyncService.syncUserFromJwt(jwt);
    }

}
