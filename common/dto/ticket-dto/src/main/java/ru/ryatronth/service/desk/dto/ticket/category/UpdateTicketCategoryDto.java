package ru.ryatronth.service.desk.dto.ticket.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для обновления категории обращений")
public record UpdateTicketCategoryDto(

        @Schema(description = "Название категории", example = "Проблемы с сетью (обновлено)")
        String name,

        @Schema(description = "Описание категории", example = "Инциденты, связанные с недоступностью сети и VPN.")
        String description,

        @Schema(description = "Приоритет обращений данной категории", example = "MEDIUM")
        TicketPriority priority,

        @Schema(description = "Базовый дедлайн в часах", example = "12")
        Integer deadlineHours,

        @Schema(description = "Дополнительные параметры категории")
        TicketCategoryParamsDto params,

        @Schema(description = "Признак активности категории", example = "false")
        Boolean active
) {}
