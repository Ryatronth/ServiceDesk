package ru.ryatronth.service.desk.data.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressStatus {
    NEW("Новое"),
    IN_PROGRESS("В работе"),
    UNDER_APPROVAL("На согласовании"),
    UNDER_REVISION("На доработке"),
    COMPLETED("Завершено"),
    CANCELLED("Отклонено");

    private final String value;

}
