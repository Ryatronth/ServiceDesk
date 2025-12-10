package ru.ryatronth.service.desk.dto.ticket.category;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Категория обращений (тикетов)")
public record TicketCategoryDto(

        @Schema(description = "ID категории", example = "b4d9b26b-1b0b-4c6b-b0e1-5f27e55a9b1c")
        UUID id,

        @Schema(description = "Название категории (уникальное)", example = "Проблемы с сетью")
        String name,

        @Schema(description = "Описание категории", example = "Инциденты, связанные с недоступностью сети.")
        String description,

        @Schema(description = "Приоритет обращений данной категории", example = "HIGH")
        TicketPriority priority,

        @Schema(description = "Базовый дедлайн в часах", example = "24")
        Integer deadlineHours,

        @Schema(description = "Дополнительные параметры категории")
        TicketCategoryParamsDto params,

        @Schema(description = "Активна ли категория", example = "true")
        boolean active,

        @Schema(description = "Дата создания", example = "2025-01-01T10:00:00Z")
        Instant createdAt,

        @Schema(description = "Дата последнего обновления", example = "2025-01-02T12:30:00Z")
        Instant updatedAt
) {}
