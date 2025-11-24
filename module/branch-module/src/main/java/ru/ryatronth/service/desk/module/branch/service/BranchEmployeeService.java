package ru.ryatronth.service.desk.module.branch.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.adapter.persona.PersonaAdapter;
import ru.ryatronth.service.desk.adapter.ticket.TicketCategoryAdapter;
import ru.ryatronth.service.desk.data.branch.model.branch.Branch;
import ru.ryatronth.service.desk.data.branch.model.branch.BranchRepository;
import ru.ryatronth.service.desk.data.branch.model.category.BranchEmployeeCategory;
import ru.ryatronth.service.desk.data.branch.model.category.BranchEmployeeCategoryRepository;
import ru.ryatronth.service.desk.dto.employee.AssignEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeDto;
import ru.ryatronth.service.desk.dto.persona.UserDto;
import ru.ryatronth.service.desk.dto.ticket.TicketCategoryDto;
import ru.ryatronth.service.desk.module.branch.mapper.BranchEmployeeMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BranchEmployeeService {

    private final BranchRepository branchRepository;
    private final BranchEmployeeCategoryRepository branchEmployeeCategoryRepository;
    private final BranchEmployeeMapper branchEmployeeMapper;
    private final PersonaAdapter personaAdapter;
    private final TicketCategoryAdapter ticketCategoryAdapter;

    @Transactional(readOnly = true)
    public Page<BranchEmployeeDto> getEmployees(UUID branchId, Pageable pageable) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found: " + branchId));

        String branchCode = branch.getCode().getCode();

        Page<UserDto> usersPage = personaAdapter.getByBranchCode(branchCode, pageable);
        List<UserDto> users = usersPage.getContent();

        if (users.isEmpty()) {
            return new PageImpl<>(List.of(), pageable, usersPage.getTotalElements());
        }

        List<UUID> userIds = users.stream()
                .map(UserDto::id)
                .toList();

        List<BranchEmployeeCategory> allCategories =
                branchEmployeeCategoryRepository.findByUserIdIn(userIds);

        Map<UUID, List<BranchEmployeeCategory>> categoriesByUserId =
                allCategories.stream()
                        .collect(Collectors.groupingBy(BranchEmployeeCategory::getUserId));

        Set<UUID> categoryIds = allCategories.stream()
                .map(BranchEmployeeCategory::getCategoryId)
                .collect(Collectors.toSet());

        Map<UUID, TicketCategoryDto> ticketCategoriesById = categoryIds.isEmpty()
                ? Map.of()
                : ticketCategoryAdapter.getCategoriesByIds(new ArrayList<>(categoryIds));

        List<BranchEmployeeDto> result = users.stream()
                .map(user -> branchEmployeeMapper.mapToDto(branchId, user, categoriesByUserId, ticketCategoriesById))
                .toList();

        return new PageImpl<>(result, pageable, usersPage.getTotalElements());
    }

    @Transactional
    public BranchEmployeeCategoryDto assignEmployeeCategory(UUID branchId,
                                                            UUID userId,
                                                            AssignEmployeeCategoryDto dto) {
        BranchEmployeeCategory entity = BranchEmployeeCategory.builder()
                .userId(userId)
                .categoryId(dto.getCategoryId())
                .build();

        BranchEmployeeCategory saved = branchEmployeeCategoryRepository.save(entity);

        Map<UUID, TicketCategoryDto> catMap =
                ticketCategoryAdapter.getCategoriesByIds(List.of(dto.getCategoryId()));
        TicketCategoryDto categoryDto = catMap.get(dto.getCategoryId());

        return BranchEmployeeCategoryDto.builder()
                .id(saved.getId())
                .category(categoryDto)
                .build();
    }

    @Transactional
    public void deleteEmployeeCategories(UUID branchId, UUID userId, UUID categoryId) {
        branchEmployeeCategoryRepository.deleteById(categoryId);
    }

}
