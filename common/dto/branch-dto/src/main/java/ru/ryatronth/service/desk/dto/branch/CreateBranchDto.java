package ru.ryatronth.service.desk.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Данные для создания филиала")
public record CreateBranchDto(

        @Schema(description = "ID родительского филиала (если есть)", example = "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")
        UUID parentId,

        @Schema(description = "ID типа филиала", example = "dddddddd-dddd-dddd-dddd-dddddddddddd")
        UUID typeId,

        @Schema(description = "ID кода филиала", example = "eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee")
        UUID codeId,

        @Schema(description = "Название филиала", example = "Филиал №1")
        String name,

        @Schema(description = "Регион/область филиала", example = "Свердловская область")
        String area,

        @Schema(description = "Адрес филиала", example = "г. Екатеринбург, ул. Ленина, д. 10")
        String address
) {}
