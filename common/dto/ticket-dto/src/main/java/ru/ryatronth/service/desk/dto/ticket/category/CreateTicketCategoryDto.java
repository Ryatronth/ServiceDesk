package ru.ryatronth.service.desk.dto.ticket.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания категории обращений")
public record CreateTicketCategoryDto(

        @Schema(description = "Название категории (уникальное)", example = "Проблемы с сетью")
        String name,

        @Schema(description = "Описание категории", example = "Инциденты, связанные с недоступностью сети.")
        String description,

        @Schema(description = "Приоритет обращений данной категории", example = "HIGH")
        TicketPriority priority,

        @Schema(description = "Базовый дедлайн в часах", example = "24")
        Integer deadlineHours,

        @Schema(description = "Дополнительные параметры категории", implementation = TicketCategoryParamsDto.class)
        TicketCategoryParamsDto params,

        @Schema(description = "Признак активности категории. Если не указано — по умолчанию true", example = "true")
        Boolean active
) {}
