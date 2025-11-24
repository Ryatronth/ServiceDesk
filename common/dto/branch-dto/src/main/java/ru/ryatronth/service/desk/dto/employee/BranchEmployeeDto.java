package ru.ryatronth.service.desk.dto.employee;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryatronth.service.desk.dto.persona.UserDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchEmployeeDto {

    private UUID id;

    private UUID branchId;

    private UserDto user;

    private List<BranchEmployeeCategoryDto> categories;

}
