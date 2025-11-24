package ru.ryatronth.service.desk.dto.employee;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryatronth.service.desk.dto.ticket.TicketCategoryDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchEmployeeCategoryDto {

    private UUID id;

    private TicketCategoryDto category;

}
