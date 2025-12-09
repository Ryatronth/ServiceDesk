package ru.ryatronth.service.desk.dto.branch;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryatronth.service.desk.dto.type.BranchTypeDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchParentDto {

    private UUID id;

    private BranchTypeDto type;

    private BranchCodeDto code;

    private String name;

    private String area;

    private String address;

}
