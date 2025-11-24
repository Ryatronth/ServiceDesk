package ru.ryatronth.service.desk.adapter.persona;

import java.util.Map;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Map<UUID, UserDto> getByIds(List<UUID> ids) {
        return userService.getByIds(ids);
    }

    @Override
    public Page<UserDto> getByBranchCode(String branchCode, Pageable pageable) {
        return userService.getByBranchCode(branchCode, pageable);
    }

}
