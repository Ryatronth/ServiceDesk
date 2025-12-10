package ru.ryatronth.service.desk.module.ticket.service.category;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.data.ticket.model.category.TicketCategory;
import ru.ryatronth.service.desk.data.ticket.model.category.TicketCategoryRepository;
import ru.ryatronth.service.desk.data.ticket.model.category.TicketCategorySpecificationBuilder;
import ru.ryatronth.service.desk.dto.ticket.category.CreateTicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.UpdateTicketCategoryDto;
import ru.ryatronth.service.desk.module.ticket.api.v1.category.filter.TicketCategoryFilterDto;
import ru.ryatronth.service.desk.module.ticket.mapper.category.TicketCategoryMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;
    private final TicketCategoryMapper ticketCategoryMapper;

    @Transactional(readOnly = true)
    public Page<TicketCategoryDto> getCategories(TicketCategoryFilterDto filters, Pageable pageable) {
        var builder = new TicketCategorySpecificationBuilder();

        if (filters != null) {
            builder.name(filters.name()).description(filters.description());
        }

        var spec = builder.build();

        return ticketCategoryRepository.findAll(spec, pageable).map(ticketCategoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Map<UUID, TicketCategoryDto> getCategoriesByIds(List<UUID> ids) {
        return ticketCategoryRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(TicketCategory::getId, ticketCategoryMapper::toDto));
    }

    @Transactional(readOnly = true)
    public TicketCategoryDto getById(UUID id) {
        TicketCategory category = ticketCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TicketCategory not found: " + id));
        return ticketCategoryMapper.toDto(category);
    }

    @Transactional
    public TicketCategoryDto create(CreateTicketCategoryDto dto) {
        TicketCategory entity = ticketCategoryMapper.toEntity(dto);
        TicketCategory saved = ticketCategoryRepository.save(entity);
        return ticketCategoryMapper.toDto(saved);
    }

    @Transactional
    public TicketCategoryDto update(UUID id, UpdateTicketCategoryDto dto) {
        TicketCategory entity = ticketCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TicketCategory not found: " + id));

        ticketCategoryMapper.updateEntityFromDto(dto, entity);
        ticketCategoryMapper.applyActive(dto, entity);

        TicketCategory saved = ticketCategoryRepository.save(entity);
        return ticketCategoryMapper.toDto(saved);
    }

    @Transactional
    public void delete(UUID id) {
        if (!ticketCategoryRepository.existsById(id)) {
            throw new EntityNotFoundException("TicketCategory not found: " + id);
        }
        ticketCategoryRepository.deleteById(id);
    }

}
