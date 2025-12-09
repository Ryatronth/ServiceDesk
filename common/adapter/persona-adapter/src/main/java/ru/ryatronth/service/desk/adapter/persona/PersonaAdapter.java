package ru.ryatronth.service.desk.adapter.persona;

import java.util.Map;
import ru.ryatronth.service.desk.dto.persona.UserDto;

import java.util.List;
import java.util.UUID;
import ru.ryatronth.service.desk.module.persona.UserFilterDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonaAdapter {

    UserDto getById(UUID id);

    Map<UUID, UserDto> getByIds(List<UUID> ids);

    Page<UserDto> getByFilters(UserFilterDto filters, Pageable pageable);

}
