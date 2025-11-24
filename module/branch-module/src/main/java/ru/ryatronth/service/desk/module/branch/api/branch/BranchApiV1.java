package ru.ryatronth.service.desk.module.branch.api.branch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.branch.BranchDto;
import ru.ryatronth.service.desk.dto.branch.CreateBranchDto;
import ru.ryatronth.service.desk.dto.branch.UpdateBranchDto;

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

@Tag(name = "Branches", description = "Управление филиалами и сотрудниками филиалов")
@RequestMapping("/api/v1/branches")
public interface BranchApiV1 {

    @Operation(summary = "Получить филиал по ID",
            description = "Возвращает информацию о филиале, включая сотрудников и контакты.")
    @ApiResponse(responseCode = "200",
            description = "Информация о филиале",
            content = @Content(schema = @Schema(implementation = BranchDto.class)))
    @GetMapping("/{branchId}")
    ResponseEntity<BranchDto> getById(@Parameter(description = "ID филиала",
            required = true) @PathVariable UUID branchId);

    @Operation(summary = "Получить список филиалов",
            description = "Возвращает список филиалов по фильтрам (если указаны).")
    @ApiResponse(responseCode = "200",
            description = "Список филиалов",
            content = @Content(schema = @Schema(implementation = BranchDto.class)))
    @GetMapping
    ResponseEntity<Page<BranchDto>> getByFilters(
            @ParameterObject Pageable pageable);

    @Operation(summary = "Создать новый филиал",
            description = "Создаёт филиал с основными данными — названием, адресом и контактами.")
    @ApiResponse(responseCode = "201",
            description = "Филиал успешно создан",
            content = @Content(schema = @Schema(implementation = BranchDto.class)))
    @PostMapping
    ResponseEntity<BranchDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для создания филиала",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateBranchDto.class)))
            @RequestBody CreateBranchDto dto);

    @Operation(summary = "Обновить данные филиала", description = "Позволяет обновить основную информацию о филиале.")
    @ApiResponse(responseCode = "200", description = "Данные филиала обновлены")
    @PutMapping("/{branchId}")
    ResponseEntity<BranchDto> update(@Parameter(description = "ID филиала",
                                             required = true) @PathVariable UUID branchId,
                                     @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для обновления филиала",
                                             required = true,
                                             content = @Content(schema = @Schema(implementation = UpdateBranchDto.class)))
                                     @RequestBody UpdateBranchDto dto);

    @Operation(summary = "Удалить филиал", description = "Удаляет филиал и всех его сотрудников.")
    @ApiResponse(responseCode = "204", description = "Филиал удалён")
    @DeleteMapping("/{branchId}")
    ResponseEntity<Void> delete(
            @Parameter(description = "ID филиала", required = true)
            @PathVariable UUID branchId);

}
