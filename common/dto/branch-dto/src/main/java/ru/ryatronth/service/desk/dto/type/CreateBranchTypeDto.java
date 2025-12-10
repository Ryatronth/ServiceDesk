package ru.ryatronth.service.desk.dto.type;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания типа филиала")
public record CreateBranchTypeDto(

        @Schema(description = "Название типа филиала", example = "Головной офис")
        String name
) {}
