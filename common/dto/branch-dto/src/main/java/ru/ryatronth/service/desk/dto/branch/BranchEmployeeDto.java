package ru.ryatronth.service.desk.dto.branch;

import ru.ryatronth.service.desk.dto.persona.UserDto;

import java.util.UUID;

public record BranchEmployeeDto(
    UUID id,
    UserDto user,
    EmployeeClientRole role
) {}
