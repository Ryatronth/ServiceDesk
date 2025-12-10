package ru.ryatronth.service.desk.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Код филиала")
public record BranchCodeDto(

        @Schema(description = "ID кода филиала", example = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        UUID id,

        @Schema(description = "Уникальный код филиала", example = "BR-001")
        String code
) {}
