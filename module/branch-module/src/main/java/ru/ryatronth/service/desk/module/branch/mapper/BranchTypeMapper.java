package ru.ryatronth.service.desk.module.branch.mapper;

import org.mapstruct.Mapper;
import ru.ryatronth.service.desk.data.branch.model.type.BranchType;
import ru.ryatronth.service.desk.dto.branch.BranchTypeDto;

@Mapper(componentModel = "spring")
public interface BranchTypeMapper {

    BranchTypeDto toDto(BranchType entity);

}
