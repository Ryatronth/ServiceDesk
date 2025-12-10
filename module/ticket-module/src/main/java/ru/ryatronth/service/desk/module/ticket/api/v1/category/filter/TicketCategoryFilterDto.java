package ru.ryatronth.service.desk.module.ticket.api.v1.category.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TicketCategoryFilterDto(
        @Schema(description = "Часть названия категории (поиск по подстроке, без учёта регистра)", example = "сеть")
        String name,

        @Schema(description = "Часть описания категории (поиск по подстроке, без учёта регистра)", example = "доступность")
        String description
) { }
