package ru.ryatronth.service.desk.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import ru.ryatronth.service.desk.dto.persona.UserDto;

@Builder
@Schema(description = "Сотрудник филиала с назначенными категориями")
public record BranchEmployeeDto(

        @Schema(description = "ID сотрудника филиала", example = "44444444-4444-4444-4444-444444444444")
        UUID id,

        @Schema(description = "ID филиала, к которому привязан сотрудник", example = "55555555-5555-5555-5555-555555555555")
        UUID branchId,

        @Schema(description = "Пользователь, связанный с сотрудником")
        UserDto user,

        @Schema(description = "Категории тикетов, назначенные сотруднику")
        List<BranchEmployeeCategoryDto> categories
) {}
