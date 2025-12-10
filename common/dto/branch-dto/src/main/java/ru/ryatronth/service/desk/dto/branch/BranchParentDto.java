package ru.ryatronth.service.desk.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.type.BranchTypeDto;

@Schema(description = "Информация о родительском филиале")
public record BranchParentDto(

        @Schema(description = "ID родительского филиала", example = "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")
        UUID id,

        @Schema(description = "Тип родительского филиала")
        BranchTypeDto type,

        @Schema(description = "Код родительского филиала")
        BranchCodeDto code,

        @Schema(description = "Название родительского филиала", example = "Головной офис")
        String name,

        @Schema(description = "Регион/область родительского филиала", example = "Москва")
        String area,

        @Schema(description = "Адрес родительского филиала", example = "г. Москва, ул. Пушкина, д. 1")
        String address
) {}
