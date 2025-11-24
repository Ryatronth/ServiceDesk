package ru.ryatronth.service.desk.module.branch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ryatronth.service.desk.data.branch.model.branch.Branch;
import ru.ryatronth.service.desk.data.branch.model.code.BranchCode;
import ru.ryatronth.service.desk.data.branch.model.type.BranchType;
import ru.ryatronth.service.desk.dto.branch.BranchCodeDto;
import ru.ryatronth.service.desk.dto.branch.BranchDto;
import ru.ryatronth.service.desk.dto.branch.BranchParentDto;
import ru.ryatronth.service.desk.dto.branch.BranchTypeDto;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(source = "parent", target = "parent")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "code", target = "code")
    BranchDto toDto(Branch entity);

    BranchParentDto toParentDto(Branch entity);

    BranchTypeDto toDto(BranchType type);

    BranchCodeDto toDto(BranchCode code);

}