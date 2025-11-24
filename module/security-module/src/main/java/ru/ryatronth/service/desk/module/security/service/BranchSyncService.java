package ru.ryatronth.service.desk.module.security.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ru.ryatronth.service.desk.data.branch.model.code.BranchCode;
import ru.ryatronth.service.desk.data.branch.model.code.BranchCodeRepository;
import ru.ryatronth.service.desk.dto.keycloak.KeycloakGroupRepresentation;
import ru.ryatronth.service.desk.http.client.keycloak.KeycloakClient;

import java.util.List;
import ru.ryatronth.service.desk.http.client.keycloak.config.properties.KeycloakClientProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class BranchSyncService {

    private final KeycloakClient keycloakClient;
    private final KeycloakClientProperties keycloakClientProperties;
    private final BranchCodeRepository branchCodeRepository;

    @Transactional
    public void syncBranches() {
        log.info("Start sync branches");
        List<KeycloakGroupRepresentation> groups = keycloakClient.getGroups(keycloakClientProperties.realm());

        KeycloakGroupRepresentation branchesRoot = groups.stream()
                .filter(g -> "branch".equals(g.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Keycloak: group 'branch' not found"));

        syncBranchChildren(branchesRoot);
    }

    private void syncBranchChildren(KeycloakGroupRepresentation parentGroup) {
        List<KeycloakGroupRepresentation> children =
                keycloakClient.getGroupChildren(keycloakClientProperties.realm(), parentGroup.id());

        for (KeycloakGroupRepresentation child : children) {
            branchCodeRepository.findByCode(child.name())
                    .orElseGet(() -> branchCodeRepository.save(
                            BranchCode.builder().id(UUID.fromString(child.id())).code(child.name()).build()
                    ));
        }
    }

    @Transactional(readOnly = true)
    public List<String> getAllBranchCodes() {
        return branchCodeRepository.findAll().stream()
                .map(BranchCode::getCode)
                .sorted()
                .toList();
    }

}
