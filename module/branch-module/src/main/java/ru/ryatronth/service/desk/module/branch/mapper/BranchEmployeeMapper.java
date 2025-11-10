package ru.ryatronth.service.desk.module.branch.mapper;

import org.mapstruct.*;
import ru.ryatronth.service.desk.data.branch.model.employee.BranchEmployee;
import ru.ryatronth.service.desk.dto.branch.BranchEmployeeDto;
import ru.ryatronth.service.desk.dto.persona.UserDto;

@Mapper(componentModel = "spring")
public interface BranchEmployeeMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "role", source = "entity.role")
    BranchEmployeeDto toDto(BranchEmployee entity, UserDto user);
}
