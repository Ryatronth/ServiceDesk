package ru.ryatronth.service.desk.dto.branch;

public record CreateBranchContactDto(
    ContactClientType type,
    String value,
    String description
) {}
