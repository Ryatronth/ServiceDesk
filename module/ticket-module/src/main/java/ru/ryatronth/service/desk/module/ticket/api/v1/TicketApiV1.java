package ru.ryatronth.service.desk.module.ticket.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryatronth.service.desk.dto.ticket.TicketCreateDto;
import ru.ryatronth.service.desk.dto.ticket.TicketDto;
import ru.ryatronth.service.desk.dto.ticket.TicketUpdateDto;

import java.util.Collection;
import java.util.UUID;

@RequestMapping("/api/v1/tickets")
public interface TicketApiV1 {

    @PostMapping
    TicketDto create(TicketCreateDto dto);

    @PutMapping("/{ticketId}")
    TicketDto update(@PathVariable UUID ticketId, TicketUpdateDto dto);

    @GetMapping("/{ticketId}")
    TicketDto getById(@PathVariable UUID ticketId);

    @GetMapping
    Collection<TicketDto> getByFilters();

    @PatchMapping("/{ticketId}/close")
    TicketDto close(@PathVariable UUID ticketId);

}
