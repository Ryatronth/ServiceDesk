package ru.ryatronth.service.desk.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.type.BranchTypeDto;

@Schema(description = "Полная информация о филиале")
public record BranchDto(

        @Schema(description = "ID филиала", example = "cccccccc-cccc-cccc-cccc-cccccccccccc")
        UUID id,

        @Schema(description = "Родительский филиал")
        BranchParentDto parent,

        @Schema(description = "Тип филиала")
        BranchTypeDto type,

        @Schema(description = "Код филиала")
        BranchCodeDto code,

        @Schema(description = "Название филиала", example = "Филиал №1")
        String name,

        @Schema(description = "Регион/область филиала", example = "Свердловская область")
        String area,

        @Schema(description = "Адрес филиала", example = "г. Екатеринбург, ул. Ленина, д. 10")
        String address
) {}
