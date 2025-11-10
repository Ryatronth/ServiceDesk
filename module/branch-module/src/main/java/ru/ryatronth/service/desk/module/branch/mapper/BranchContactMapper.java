package ru.ryatronth.service.desk.module.branch.mapper;

import org.mapstruct.Mapper;
import ru.ryatronth.service.desk.data.branch.model.contact.BranchContact;
import ru.ryatronth.service.desk.dto.branch.BranchContactDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchContactMapper {

    BranchContactDto toDto(BranchContact entity);

    List<BranchContactDto> toDtoList(List<BranchContact> entities);

}
