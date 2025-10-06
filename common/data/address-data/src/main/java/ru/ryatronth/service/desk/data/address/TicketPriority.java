package ru.ryatronth.service.desk.data.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TicketPriority {
    LOW("Низкий"),
    NORMAL("Нормальный"),
    HIGH("Высокий"),
    CRITICAL("Критический");

    private final String value;

}
