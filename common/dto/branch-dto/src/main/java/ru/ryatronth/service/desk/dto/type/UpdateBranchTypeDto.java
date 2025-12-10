package ru.ryatronth.service.desk.dto.type;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для обновления типа филиала")
public record UpdateBranchTypeDto(

        @Schema(description = "Название типа филиала", example = "Региональный офис")
        String name
) {}
