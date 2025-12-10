package ru.ryatronth.service.desk.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Запрос на назначение категории сотруднику филиала")
public record AssignEmployeeCategoryDto(

        @Schema(description = "ID категории тикета, которую нужно назначить сотруднику", example = "22222222-2222-2222-2222-222222222222")
        UUID categoryId
) {}
