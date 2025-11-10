package ru.ryatronth.service.desk.dto.branch;

import java.util.List;
import java.util.UUID;

public record BranchDto(
    UUID id,
    BranchDto parent,
    BranchEmployeeDto manager,
    BranchTypeDto type,
    String name,
    String address,
    List<BranchContactDto> contacts
) {}
