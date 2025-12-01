package ru.ryatronth.service.desk.adapter.ticket;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.ticket.TicketCategoryDto;

import org.springframework.stereotype.Component;

@Component
public class TicketCategoryAdapterImpl implements TicketCategoryAdapter {

    @Override
    public Map<UUID, TicketCategoryDto> getCategoriesByIds(List<UUID> ids) {
        return Map.of();
    }

}
