package ru.ryatronth.service.desk.module.branch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ryatronth.service.desk.data.branch.model.employee.assign.category.BranchEmployeeCategory;
import ru.ryatronth.service.desk.dto.branch.BranchEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.TicketCategoryDto;

@Mapper(componentModel = "spring")
public interface BranchEmployeeCategoryMapper {

    @Mapping(target = "category", source = "category")
    BranchEmployeeCategoryDto toDto(BranchEmployeeCategory entity, TicketCategoryDto category);
}
