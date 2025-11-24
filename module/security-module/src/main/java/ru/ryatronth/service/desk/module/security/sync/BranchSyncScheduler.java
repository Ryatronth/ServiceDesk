package ru.ryatronth.service.desk.module.security.sync;

import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.module.security.service.BranchSyncService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchSyncScheduler {

    private final BranchSyncService branchSyncService;

    @Scheduled(cron = "${sync.scheduling.branch-sync-cron:0/15 * * * * *}")
    public void scheduledBranchSync() {
        branchSyncService.syncBranches();
    }

}
