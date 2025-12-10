package ru.ryatronth.service.desk.module.ticket.api.v1.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.ticket.category.CreateTicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.TicketCategoryDto;
import ru.ryatronth.service.desk.dto.ticket.category.UpdateTicketCategoryDto;
import ru.ryatronth.service.desk.module.ticket.api.v1.category.filter.TicketCategoryFilterDto;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "Ticket categories",
        description = "Управление категориями обращений (тикетов)"
)
@RequestMapping("/api/v1/ticket-categories")
public interface TicketCategoryApiV1 {

    @Operation(
            summary = "Получить список категорий обращений",
            description = "Возвращает список категорий обращений с пагинацией и возможностью фильтрации по названию и описанию."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Список категорий",
            content = @Content(schema = @Schema(implementation = TicketCategoryDto.class))
    )
    @GetMapping
    ResponseEntity<Page<TicketCategoryDto>> getCategories(
            @ParameterObject TicketCategoryFilterDto filters,
            @ParameterObject Pageable pageable
    );

    @GetMapping("/{id}")
    ResponseEntity<TicketCategoryDto> getCategoryById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<TicketCategoryDto> createCategory(@RequestBody CreateTicketCategoryDto dto);

    @PutMapping("/{id}")
    ResponseEntity<TicketCategoryDto> updateCategory(@PathVariable UUID id, @RequestBody UpdateTicketCategoryDto dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable UUID id);
}
