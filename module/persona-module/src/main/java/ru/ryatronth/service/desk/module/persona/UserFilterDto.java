package ru.ryatronth.service.desk.module.persona;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserFilterDto {
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private String workplace;
    private String branch;
}
