package ru.ryatronth.service.desk.adapter.persona;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.dto.persona.UserDto;
import ru.ryatronth.service.desk.module.persona.UserFilterDto;
import ru.ryatronth.service.desk.module.persona.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonaAdapterImpl implements PersonaAdapter {

    private final UserService userService;

    @Override
    public UserDto getById(UUID id) {
        return userService.getById(id);
    }

    @Override
    public Map<UUID, UserDto> getByIds(List<UUID> ids) {
        return userService.getByIds(ids);
    }

    @Override
    public Page<UserDto> getByFilters(UserFilterDto filters, Pageable pageable) {
        return userService.getByFilters(filters, pageable);
    }

}
