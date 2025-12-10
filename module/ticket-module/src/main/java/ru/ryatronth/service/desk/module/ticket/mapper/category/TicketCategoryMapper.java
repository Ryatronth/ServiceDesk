package ru.ryatronth.service.desk.module.ticket.mapper.category;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ryatronth.service.desk.data.ticket.model.category.TicketCategory;
import ru.ryatronth.service.desk.data.ticket.model.category.TicketCategoryParams;
import ru.ryatronth.service.desk.dto.ticket.category.CreateTicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryParamsDto;
import ru.ryatronth.service.desk.dto.ticket.category.UpdateTicketCategoryDto;

@Mapper(componentModel = "spring")
public interface TicketCategoryMapper {

    TicketCategoryDto toDto(TicketCategory entity);

    List<TicketCategoryDto> toDtoList(List<TicketCategory> entities);

    TicketCategoryParamsDto toParamsDto(TicketCategoryParams params);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", expression = "java(dto.active() != null ? dto.active() : true)")
    TicketCategory toEntity(CreateTicketCategoryDto dto);

    TicketCategoryParams toParamsEntity(TicketCategoryParamsDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntityFromDto(UpdateTicketCategoryDto dto, @MappingTarget TicketCategory entity);

    default void applyActive(UpdateTicketCategoryDto dto, @MappingTarget TicketCategory entity) {
        if (dto.active() != null) {
            entity.setActive(dto.active());
        }
    }
}
