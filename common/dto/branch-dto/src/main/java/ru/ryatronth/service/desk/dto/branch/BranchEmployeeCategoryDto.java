package ru.ryatronth.service.desk.dto.branch;

import ru.ryatronth.service.desk.dto.ticket.TicketCategoryDto;

import java.util.UUID;

public record BranchEmployeeCategoryDto(
    UUID id,
    TicketCategoryDto category
) {}
