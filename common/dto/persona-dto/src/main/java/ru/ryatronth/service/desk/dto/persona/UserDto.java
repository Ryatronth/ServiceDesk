package ru.ryatronth.service.desk.dto.persona;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Информация о пользователе системы")
public record UserDto(
    @Schema(description = "Идентификатор пользователя (UUID)", example = "123e4567-e89b-12d3-a456-426614174000") UUID id,
    @Schema(description = "Адрес электронной почты", example = "ivan.petrov@example.com") String email,
    @Schema(description = "Имя пользователя", example = "Иван") String firstName,
    @Schema(description = "Фамилия пользователя", example = "Петров") String lastName,
    @Schema(description = "Отчество пользователя", example = "Игоревич") String patronymic,
    @Schema(description = "Рабочее место") String workplace,
    @Schema(description = "Код филиала, к которому относится пользователь", example = "c74b9273-f8d9-4c1f-91c2-4fdd59e1cc0a") String branch,
    @Schema(description = "Признак активности пользователя", example = "true") boolean enabled,
    @Schema(description = "Список ролей пользователя", example = "[\"ROLE_ADMIN\", \"ROLE_USER\"]") List<String> roles) {}
