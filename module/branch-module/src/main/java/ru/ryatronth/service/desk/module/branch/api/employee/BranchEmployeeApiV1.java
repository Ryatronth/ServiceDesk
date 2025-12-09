package ru.ryatronth.service.desk.module.branch.api.employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.employee.AssignEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeDto;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Branches", description = "Управление филиалами и сотрудниками филиалов")
@RequestMapping("/api/v1/branches/{branchId}/employees")
public interface BranchEmployeeApiV1 {

    @Operation(summary = "Получить сотрудников филиала", description = "Возвращает список сотрудников филиала с пагинацией.")
    @ApiResponse(responseCode = "200", description = "Список сотрудников", content = @Content(schema = @Schema(implementation = BranchEmployeeDto.class)))
    @GetMapping
    ResponseEntity<Page<BranchEmployeeDto>> getEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String patronymic,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String workplace,
            @Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId,
            @ParameterObject Pageable pageable);

    @Operation(summary = "Назначить сотруднику категорию", description = "Привязывает категорию заявок к сотруднику филиала.")
    @ApiResponse(responseCode = "200", description = "Категория успешно назначена")
    @PostMapping("/{employeeId}/categories")
    ResponseEntity<BranchEmployeeCategoryDto> assignEmployeeCategory(
            @Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId,
            @Parameter(description = "ID сотрудника", required = true) @PathVariable UUID employeeId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Категория для назначения", required = true, content = @Content(schema = @Schema(implementation = AssignEmployeeCategoryDto.class)))
            @RequestBody AssignEmployeeCategoryDto dto);

    @Operation(summary = "Удалить категорию сотрудника", description = "Удаляет категорию заявок, назначенную сотруднику филиала.")
    @ApiResponse(responseCode = "204", description = "Категория удалена")
    @DeleteMapping("/{employeeId}/categories/{categoryId}")
    ResponseEntity<Void> deleteEmployeeCategory(
            @Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId,
            @Parameter(description = "ID сотрудника", required = true) @PathVariable UUID employeeId,
            @Parameter(description = "ID связи", required = true) @PathVariable UUID categoryId);


}
