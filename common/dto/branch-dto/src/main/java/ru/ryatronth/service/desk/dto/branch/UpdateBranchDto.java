package ru.ryatronth.service.desk.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Данные для обновления филиала")
public record UpdateBranchDto(

        @Schema(description = "ID родительского филиала (если меняется)", example = "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")
        UUID parentId,

        @Schema(description = "ID типа филиала (если меняется)", example = "dddddddd-dddd-dddd-dddd-dddddddddddd")
        UUID typeId,

        @Schema(description = "ID кода филиала (если меняется)", example = "eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee")
        UUID codeId,

        @Schema(description = "Название филиала", example = "Филиал №1 (обновлённый)")
        String name,

        @Schema(description = "Регион/область филиала", example = "Свердловская область")
        String area,

        @Schema(description = "Адрес филиала", example = "г. Екатеринбург, ул. Ленина, д. 15")
        String address
) {}
