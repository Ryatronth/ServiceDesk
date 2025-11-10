package ru.ryatronth.service.desk.module.branch.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ryatronth.service.desk.dto.branch.*;

import java.util.UUID;

@Tag(name = "Branches", description = "Управление филиалами и сотрудниками филиалов")
@RequestMapping("/api/v1/branches")
public interface BranchApiV1 {

    @Operation(summary = "Получить филиал по ID", description = "Возвращает информацию о филиале, включая сотрудников и контакты.")
    @ApiResponse(responseCode = "200", description = "Информация о филиале", content = @Content(schema = @Schema(implementation = BranchDto.class)))
    @GetMapping("/{branchId}")
    ResponseEntity<BranchDto> getById(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId);

    @Operation(summary = "Получить список филиалов", description = "Возвращает список филиалов по фильтрам (если указаны).")
    @ApiResponse(responseCode = "200", description = "Список филиалов", content = @Content(schema = @Schema(implementation = BranchDto.class)))
    @GetMapping
    ResponseEntity<Page<BranchDto>> getByFilters(@ParameterObject Pageable pageable);

    @Operation(summary = "Создать новый филиал", description = "Создаёт филиал с основными данными — названием, адресом и контактами.")
    @ApiResponse(responseCode = "201", description = "Филиал успешно создан", content = @Content(schema = @Schema(implementation = BranchDto.class)))
    @PostMapping
    ResponseEntity<BranchDto> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для создания филиала", required = true, content = @Content(schema = @Schema(implementation = CreateBranchDto.class))) @RequestBody CreateBranchDto dto);

    @Operation(summary = "Обновить данные филиала", description = "Позволяет обновить основную информацию о филиале.")
    @ApiResponse(responseCode = "200", description = "Данные филиала обновлены")
    @PutMapping("/{branchId}")
    ResponseEntity<BranchDto> update(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для обновления филиала", required = true, content = @Content(schema = @Schema(implementation = UpdateBranchDto.class))) @RequestBody UpdateBranchDto dto);

    @Operation(summary = "Удалить филиал", description = "Удаляет филиал и всех его сотрудников.")
    @ApiResponse(responseCode = "204", description = "Филиал удалён")
    @DeleteMapping("/{branchId}")
    ResponseEntity<Void> delete(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId);

    @Operation(summary = "Получить сотрудников филиала", description = "Возвращает список сотрудников филиала с пагинацией.")
    @ApiResponse(responseCode = "200", description = "Список сотрудников", content = @Content(schema = @Schema(implementation = BranchEmployeeDto.class)))
    @GetMapping("/{branchId}/employees")
    ResponseEntity<Page<BranchEmployeeDto>> getEmployees(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @ParameterObject Pageable pageable);

    @Operation(summary = "Добавить сотрудника в филиал", description = "Добавляет пользователя в указанный филиал с заданной ролью.")
    @ApiResponse(responseCode = "201", description = "Сотрудник добавлен")
    @PostMapping("/{branchId}/employees")
    ResponseEntity<BranchEmployeeDto> addEmployee(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Информация о добавляемом сотруднике", required = true, content = @Content(schema = @Schema(implementation = AddEmployeeDto.class))) @RequestBody AddEmployeeDto dto);

    @Operation(summary = "Изменить сотрудника филиала", description = "Обновляет роль или назначение сотрудника в филиале.")
    @ApiResponse(responseCode = "200", description = "Сотрудник изменён")
    @PutMapping("/{branchId}/employees/{employeeId}")
    ResponseEntity<BranchEmployeeDto> updateEmployee(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @Parameter(description = "ID сотрудника", required = true) @PathVariable UUID employeeId, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для обновления сотрудника", required = true, content = @Content(schema = @Schema(implementation = AddEmployeeDto.class))) @RequestBody AddEmployeeDto dto);

    @Operation(summary = "Удалить сотрудника из филиала", description = "Удаляет сотрудника по его ID из выбранного филиала.")
    @ApiResponse(responseCode = "204", description = "Сотрудник удалён")
    @DeleteMapping("/{branchId}/employees/{employeeId}")
    ResponseEntity<Void> deleteEmployee(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @Parameter(description = "ID сотрудника", required = true) @PathVariable UUID employeeId);

    @Operation(summary = "Получить категории сотрудника", description = "Возвращает список категорий заявок, закреплённых за сотрудником филиала.")
    @ApiResponse(responseCode = "200", description = "Список категорий сотрудника", content = @Content(schema = @Schema(implementation = BranchEmployeeCategoryDto.class)))
    @GetMapping("/{branchId}/employees/{employeeId}/categories")
    ResponseEntity<Page<BranchEmployeeCategoryDto>> getEmployeeCategories(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @Parameter(description = "ID сотрудника", required = true) @PathVariable UUID employeeId, @ParameterObject Pageable pageable);

    @Operation(summary = "Назначить сотруднику категорию", description = "Привязывает категорию заявок к сотруднику филиала.")
    @ApiResponse(responseCode = "200", description = "Категория успешно назначена")
    @PostMapping("/{branchId}/employees/{employeeId}/categories")
    ResponseEntity<BranchEmployeeCategoryDto> assignEmployeeCategory(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @Parameter(description = "ID сотрудника", required = true) @PathVariable UUID employeeId, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Категория для назначения", required = true, content = @Content(schema = @Schema(implementation = AssignEmployeeCategoryDto.class))) @RequestBody AssignEmployeeCategoryDto dto);

    @Operation(summary = "Удалить категорию сотрудника", description = "Удаляет категорию заявок, назначенную сотруднику филиала.")
    @ApiResponse(responseCode = "204", description = "Категория удалена")
    @DeleteMapping("/{branchId}/employees/{employeeId}/categories")
    ResponseEntity<Void> deleteEmployeeCategory(@Parameter(description = "ID филиала", required = true) @PathVariable UUID branchId, @Parameter(description = "ID сотрудника", required = true) @PathVariable UUID employeeId);

}
