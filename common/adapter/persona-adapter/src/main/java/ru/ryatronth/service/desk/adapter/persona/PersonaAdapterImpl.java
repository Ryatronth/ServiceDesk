package ru.ryatronth.service.desk.adapter.persona;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ryatronth.service.desk.dto.persona.UserDto;
import ru.ryatronth.service.desk.module.persona.UserService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonaAdapterImpl implements PersonaAdapter {

    private final UserService userService;

    @Override
    public UserDto getById(UUID id) {
        return userService.getById(id);
    }

    @Override
    public List<UserDto> getByIds(List<UUID> ids) {
        return userService.getBIds(ids);
    }

}
