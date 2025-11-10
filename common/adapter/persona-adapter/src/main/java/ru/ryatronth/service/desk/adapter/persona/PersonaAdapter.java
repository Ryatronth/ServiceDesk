package ru.ryatronth.service.desk.adapter.persona;

import ru.ryatronth.service.desk.dto.persona.UserDto;

import java.util.List;
import java.util.UUID;

public interface PersonaAdapter {

    UserDto getById(UUID id);

    List<UserDto> getByIds(List<UUID> ids);

}
