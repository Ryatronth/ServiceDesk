package ru.ryatronth.service.desk.module.branch.api.branch.filter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Фильтры для поиска филиалов")
public record BranchFilterDto(

        @Schema(description = "Фильтр по названию филиала (поиск по подстроке, без учёта регистра)", example = "Центральный офис")
        String name,

        @Schema(description = "Фильтр по региону/области (поиск по подстроке, без учёта регистра)", example = "Свердловская область")
        String area,

        @Schema(description = "Фильтр по адресу филиала (поиск по подстроке, без учёта регистра)", example = "ул. Ленина")
        String address
) {}
