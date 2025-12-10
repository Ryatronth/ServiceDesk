package ru.ryatronth.service.desk.dto.ticket.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Дополнительные параметры категории обращения")
public record TicketCategoryParamsDto(

        @Schema(description = "Требуется ли утверждение обращения перед выполнением", example = "false")
        boolean needApproval,

        @Schema(description = "Требуется ли прикрепление медиа (фото/видео)", example = "true")
        boolean needMedia
) {}
