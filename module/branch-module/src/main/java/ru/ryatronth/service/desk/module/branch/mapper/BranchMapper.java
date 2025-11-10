package ru.ryatronth.service.desk.module.branch.mapper;

import org.mapstruct.*;
import ru.ryatronth.service.desk.data.branch.model.branch.Branch;
import ru.ryatronth.service.desk.dto.branch.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "parent", source = "parent")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "contacts", source = "contacts")
    BranchDto toDto(
            Branch entity,
            BranchDto parent,
            BranchEmployeeDto manager,
            BranchTypeDto type,
            List<BranchContactDto> contacts
    );
}
