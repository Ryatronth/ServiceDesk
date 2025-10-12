package ru.ryatronth.service.desk.data.ticket.model.category;

import lombok.Data;

@Data
public class TicketCategoryParams {

    private boolean needApproval = false;

    private boolean needMedia = false;

}
