package ru.ryatronth.service.desk.module.security.sync;

import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.module.security.service.UserSyncService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSyncScheduler {

    private final UserSyncService userSyncService;

    @Scheduled(cron = "${sync.scheduling.user-sync-cron:0/15 * * * * *}")
    public void scheduledUserSync() {
        userSyncService.syncAllUsersFromKeycloak();
    }

}
