package ru.ryatronth.service.desk.dto.branch;

import java.util.List;
import java.util.UUID;

public record CreateBranchDto(
    UUID parentId,
    UUID typeId,
    UUID managerId,
    String name,
    String address,
    List<CreateBranchContactDto> contacts
) {}
