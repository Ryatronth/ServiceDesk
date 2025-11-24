package ru.ryatronth.service.desk.module.persona;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ryatronth.service.desk.data.persona.model.user.User;
import ru.ryatronth.service.desk.data.persona.model.user.UserRepository;
import ru.ryatronth.service.desk.dto.persona.UserDto;

import org.apache.commons.lang3.function.Functions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public UserDto create(UserDto dto, String email) {
        User user = userMapper.toEntity(dto);
        user.setEmail(email);
        user = userRepository.save(user);
        log.info("Created user {}", user.getId());
        return userMapper.toDto(user);
    }

    public UserDto getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return userMapper.toDto(user);
    }

    public Map<UUID, UserDto> getByIds(List<UUID> ids) {
        List<User> users = userRepository.findAllById(ids);
        return users.stream().map(userMapper::toDto).collect(Collectors.toMap(
                UserDto::id,
                Function.identity()
        ));
    }

    public Page<UserDto> getByBranchCode(String branchCode, Pageable pageable) {
        return userRepository.findByBranch(branchCode, pageable).map(userMapper::toDto);
    }

    public Page<UserDto> getPage(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    public Page<UserDto> search(Specification<User> specification, Pageable pageable) {
        return userRepository.findAll(specification, pageable).map(userMapper::toDto);
    }

}
