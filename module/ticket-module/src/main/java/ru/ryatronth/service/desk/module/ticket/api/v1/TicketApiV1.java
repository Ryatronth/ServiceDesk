package ru.ryatronth.service.desk.module.ticket.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryatronth.service.desk.dto.ticket.TicketCreateDto;
import ru.ryatronth.service.desk.dto.ticket.TicketDto;
import ru.ryatronth.service.desk.dto.ticket.TicketUpdateDto;

import java.util.UUID;

@RequestMapping("/api/v1/tickets")
public interface TicketApiV1 {

    @PostMapping
    ResponseEntity<TicketDto> create(TicketCreateDto dto);

    @PutMapping("/{ticketId}")
    ResponseEntity<TicketDto> update(@PathVariable UUID ticketId, TicketUpdateDto dto);

    @GetMapping("/{ticketId}")
    ResponseEntity<TicketDto> getById(@PathVariable UUID ticketId);

    @GetMapping
    ResponseEntity<Page<TicketDto>> getByFilters();

    @PatchMapping("/{ticketId}/close")
    ResponseEntity<TicketDto> close(@PathVariable UUID ticketId);

}
