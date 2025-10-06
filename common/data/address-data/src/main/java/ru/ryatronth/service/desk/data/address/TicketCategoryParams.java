package ru.ryatronth.service.desk.data.address;

import lombok.Data;

@Data
public class TicketCategoryParams {

    private boolean needApproval = false;

    private boolean needMedia = false;

}
