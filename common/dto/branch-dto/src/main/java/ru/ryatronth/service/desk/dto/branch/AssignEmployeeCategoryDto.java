package ru.ryatronth.service.desk.dto.branch;

import java.util.UUID;

public record AssignEmployeeCategoryDto(
    UUID employeeId,
    UUID categoryId
) {}
