package ru.ryatronth.service.desk.module.branch.api.branch.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BranchFilterDto {
    private String name;
    private String area;
    private String address;
}
