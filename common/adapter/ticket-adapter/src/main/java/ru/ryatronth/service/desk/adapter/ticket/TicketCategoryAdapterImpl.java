package ru.ryatronth.service.desk.adapter.ticket;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;
import ru.ryatronth.service.desk.module.ticket.service.category.TicketCategoryService;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketCategoryAdapterImpl implements TicketCategoryAdapter {

    private final TicketCategoryService ticketCategoryService;

    @Override
    public Map<UUID, TicketCategoryDto> getCategoriesByIds(List<UUID> ids) {
        return ticketCategoryService.getCategoriesByIds(ids);
    }

}
