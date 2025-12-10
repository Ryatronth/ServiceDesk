package ru.ryatronth.service.desk.dto.type;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Тип филиала")
public record BranchTypeDto(

        @Schema(description = "ID типа филиала", example = "11111111-1111-1111-1111-111111111111")
        UUID id,

        @Schema(description = "Название типа филиала", example = "Головной офис")
        String name
) {
}
