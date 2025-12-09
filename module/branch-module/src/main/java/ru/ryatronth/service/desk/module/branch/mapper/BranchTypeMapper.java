package ru.ryatronth.service.desk.module.branch.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ryatronth.service.desk.data.branch.model.type.BranchType;
import ru.ryatronth.service.desk.dto.type.BranchTypeDto;
import ru.ryatronth.service.desk.dto.type.CreateBranchTypeDto;
import ru.ryatronth.service.desk.dto.type.UpdateBranchTypeDto;

@Mapper(componentModel = "spring")
public interface BranchTypeMapper {

    BranchTypeDto toDto(BranchType entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branches", ignore = true)
    BranchType fromCreateDto(CreateBranchTypeDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branches", ignore = true)
    void updateEntityFromDto(UpdateBranchTypeDto dto, @MappingTarget BranchType entity);

}
