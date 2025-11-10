package ru.ryatronth.service.desk.dto.branch;

import java.util.UUID;

public record BranchTypeDto(
    UUID id,
    String name
) {}
