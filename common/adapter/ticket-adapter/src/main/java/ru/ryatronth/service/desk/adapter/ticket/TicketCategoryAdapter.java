package ru.ryatronth.service.desk.adapter.ticket;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;

public interface TicketCategoryAdapter {

    Map<UUID, TicketCategoryDto> getCategoriesByIds(List<UUID> ids);

}
