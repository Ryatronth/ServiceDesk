package ru.ryatronth.service.desk.data.ticket.model.ticket.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TicketStatus {
    NEW("Новое"),
    IN_PROGRESS("В работе"),
    UNDER_APPROVAL("На согласовании"),
    UNDER_REVISION("На доработке"),
    COMPLETED("Завершено"),
    CANCELLED_BY_SOLVER("Отклонено исполнителем"),
    CANCELED_BY_INITIATOR("Отменено инициатором");

    private final String value;

}
