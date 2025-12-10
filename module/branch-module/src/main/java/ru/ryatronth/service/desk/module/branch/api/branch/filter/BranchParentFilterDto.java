package ru.ryatronth.service.desk.module.branch.api.branch.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;

@Schema(description = "Фильтры для поиска филиалов, подходящих для назначения как родитель")
public record BranchParentFilterDto(

        @Schema(description = "Фильтр по названию филиала (поиск по подстроке, без учёта регистра)", example = "Центральный")
        String name,

        @Schema(description = "Фильтр по региону/области (поиск по подстроке, без учёта регистра)", example = "Свердловская область")
        String area,

        @Schema(description = "Фильтр по адресу филиала (поиск по подстроке, без учёта регистра)", example = "ул. Ленина")
        String address,

        @Schema(description = "Список ID филиалов, которые должны быть исключены из выборки")
        List<UUID> exclude
) {}
