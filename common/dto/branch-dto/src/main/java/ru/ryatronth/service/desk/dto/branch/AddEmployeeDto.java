package ru.ryatronth.service.desk.dto.branch;

import java.util.UUID;

public record AddEmployeeDto(
    UUID userId,
    EmployeeClientRole role
) {}
