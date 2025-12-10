package ru.ryatronth.service.desk.module.branch.mapper;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import ru.ryatronth.service.desk.data.branch.model.category.BranchEmployeeCategory;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeDto;
import ru.ryatronth.service.desk.dto.persona.UserDto;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;

import org.springframework.stereotype.Component;

@Component
public class BranchEmployeeMapper {

    public BranchEmployeeDto mapToDto(UUID branchId,
                                      UserDto user,
                                      Map<UUID, List<BranchEmployeeCategory>> categoriesByUserId,
                                      Map<UUID, TicketCategoryDto> ticketCategoriesById) {

        List<BranchEmployeeCategory> userCategories =
                categoriesByUserId.getOrDefault(user.id(), List.of());

        List<BranchEmployeeCategoryDto> categoryDtos = userCategories.stream()
                .map(rel -> BranchEmployeeCategoryDto.builder()
                        .id(rel.getId())
                        .category(ticketCategoriesById.get(rel.getCategoryId()))
                        .build())
                .toList();

        return BranchEmployeeDto.builder()
                .id(user.id())
                .branchId(branchId)
                .user(user)
                .categories(categoryDtos)
                .build();
    }

}
