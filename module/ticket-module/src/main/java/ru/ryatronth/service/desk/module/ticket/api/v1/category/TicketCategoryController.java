package ru.ryatronth.service.desk.module.ticket.api.v1.category;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.dto.ticket.category.CreateTicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.UpdateTicketCategoryDto;
import ru.ryatronth.service.desk.module.ticket.api.v1.category.filter.TicketCategoryFilterDto;
import ru.ryatronth.service.desk.module.ticket.service.category.TicketCategoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketCategoryController implements TicketCategoryApiV1 {

    private final TicketCategoryService ticketCategoryService;

    @Override
    public ResponseEntity<Page<TicketCategoryDto>> getCategories(TicketCategoryFilterDto filters, Pageable pageable) {
        Page<TicketCategoryDto> page = ticketCategoryService.getCategories(filters, pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<TicketCategoryDto> getCategoryById(UUID id) {
        TicketCategoryDto dto = ticketCategoryService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TicketCategoryDto> createCategory(CreateTicketCategoryDto dto) {
        TicketCategoryDto created = ticketCategoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<TicketCategoryDto> updateCategory(UUID id, UpdateTicketCategoryDto dto) {
        TicketCategoryDto updated = ticketCategoryService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(UUID id) {
        ticketCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
