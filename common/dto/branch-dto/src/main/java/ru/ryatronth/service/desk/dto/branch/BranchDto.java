package ru.ryatronth.service.desk.dto.branch;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    private UUID id;

    private BranchParentDto parent;

    private BranchTypeDto type;

    private BranchCodeDto code;

    private String name;

    private String area;

    private String address;

}
