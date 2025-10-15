package ru.ryatronth.service.desk.dto.persona;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserDto(
    UUID id,
    String email,
    String firstName,
    String lastName,
    String patronymic,
    String city,
    String address,
    String workplace,
    UUID branchId,
    boolean enabled
) {

}
