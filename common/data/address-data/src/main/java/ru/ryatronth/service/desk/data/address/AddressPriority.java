package ru.ryatronth.service.desk.data.address;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AddressPriority {
    LOW("Низкий"),
    NORMAL("Нормальный"),
    HIGH("Высокий"),
    CRITICAL("Критический");

    private final String value;

}
