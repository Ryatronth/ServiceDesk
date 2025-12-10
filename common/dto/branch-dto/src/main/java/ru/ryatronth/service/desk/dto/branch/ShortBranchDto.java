package ru.ryatronth.service.desk.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Краткая информация о филиале")
public record ShortBranchDto(

        @Schema(description = "ID филиала", example = "ffffffff-ffff-ffff-ffff-ffffffffffff")
        UUID id,

        @Schema(description = "Название филиала", example = "Филиал №1")
        String name,

        @Schema(description = "Регион/область филиала", example = "Свердловская область")
        String area,

        @Schema(description = "Адрес филиала", example = "г. Екатеринбург, ул. Ленина, д. 10")
        String address
) {}
