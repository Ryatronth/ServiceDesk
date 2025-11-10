package ru.ryatronth.service.desk.dto.branch;

import java.util.UUID;

public record BranchContactDto(
    UUID id,
    UUID branchId,
    ContactClientType type,
    String value,
    String description
) {}
