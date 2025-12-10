package ru.ryatronth.service.desk.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Builder;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;

@Builder
@Schema(description = "Категория тикетов, назначенная сотруднику филиала")
public record BranchEmployeeCategoryDto(

        @Schema(description = "ID связи сотрудник–категория", example = "33333333-3333-3333-3333-333333333333")
        UUID id,

        @Schema(description = "Информация о категории тикетов")
        TicketCategoryDto category
) {}
