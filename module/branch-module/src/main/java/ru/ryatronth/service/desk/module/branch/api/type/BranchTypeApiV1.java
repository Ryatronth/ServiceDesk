package ru.ryatronth.service.desk.module.branch.api.type;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import ru.ryatronth.service.desk.dto.type.BranchTypeDto;
import ru.ryatronth.service.desk.dto.type.CreateBranchTypeDto;
import ru.ryatronth.service.desk.dto.type.UpdateBranchTypeDto;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CRUD по типам филиалов.
 */
@Tag(name = "Branch types", description = "Управление типами филиалов")
@RequestMapping("/api/v1/branch-types")
public interface BranchTypeApiV1 {

    @Operation(summary = "Получить список типов филиалов", description = "Возвращает список типов филиалов с пагинацией.")
    @ApiResponse(responseCode = "200", description = "Список типов филиалов", content = @Content(schema = @Schema(implementation = BranchTypeDto.class)))
    @GetMapping
    ResponseEntity<Page<BranchTypeDto>> getBranchTypes(@ParameterObject Pageable pageable);

    @Operation(summary = "Получить тип филиала по ID", description = "Возвращает информацию о конкретном типе филиала.")
    @ApiResponse(responseCode = "200", description = "Тип филиала найден", content = @Content(schema = @Schema(implementation = BranchTypeDto.class)))
    @ApiResponse(responseCode = "404", description = "Тип филиала не найден")
    @GetMapping("/{id}")
    ResponseEntity<BranchTypeDto> getBranchTypeById(
            @Parameter(description = "ID типа филиала", required = true) @PathVariable UUID id);

    @Operation(summary = "Создать тип филиала", description = "Создаёт новый тип филиала.")
    @ApiResponse(responseCode = "201", description = "Тип филиала создан", content = @Content(schema = @Schema(implementation = BranchTypeDto.class)))
    @PostMapping
    ResponseEntity<BranchTypeDto> createBranchType(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для создания типа филиала", required = true, content = @Content(schema = @Schema(implementation = CreateBranchTypeDto.class)))
            @RequestBody CreateBranchTypeDto dto);

    @Operation(summary = "Обновить тип филиала", description = "Изменяет имя существующего типа филиала.")
    @ApiResponse(responseCode = "200", description = "Тип филиала обновлён", content = @Content(schema = @Schema(implementation = BranchTypeDto.class)))
    @ApiResponse(responseCode = "404", description = "Тип филиала не найден")
    @PutMapping("/{id}")
    ResponseEntity<BranchTypeDto> updateBranchType(
            @Parameter(description = "ID типа филиала", required = true) @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для обновления типа филиала", required = true, content = @Content(schema = @Schema(implementation = UpdateBranchTypeDto.class)))
            @RequestBody UpdateBranchTypeDto dto);

    @Operation(summary = "Удалить тип филиала", description = "Удаляет тип филиала по ID. " +
            "Если тип используется филиалами, может быть возвращена ошибка бизнес-валидации.")
    @ApiResponse(responseCode = "204", description = "Тип филиала удалён")
    @ApiResponse(responseCode = "404", description = "Тип филиала не найден")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBranchType(
            @Parameter(description = "ID типа филиала", required = true) @PathVariable UUID id);
}
