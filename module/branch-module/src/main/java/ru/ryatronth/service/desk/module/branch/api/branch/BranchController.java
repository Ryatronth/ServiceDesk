package ru.ryatronth.service.desk.module.branch.api.branch;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.dto.branch.BranchCodeDto;
import ru.ryatronth.service.desk.dto.branch.BranchDto;
import ru.ryatronth.service.desk.dto.branch.CreateBranchDto;
import ru.ryatronth.service.desk.dto.branch.ShortBranchDto;
import ru.ryatronth.service.desk.dto.branch.UpdateBranchDto;
import ru.ryatronth.service.desk.module.branch.api.branch.filter.BranchFilterDto;
import ru.ryatronth.service.desk.module.branch.api.branch.filter.BranchParentFilterDto;
import ru.ryatronth.service.desk.module.branch.service.BranchService;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class BranchController implements BranchApiV1 {

    private final BranchService branchService;

    @Override
    public ResponseEntity<Page<BranchCodeDto>> getBranchCodes(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(branchService.getCodes(pageable));
    }

    @Override
    public ResponseEntity<BranchDto> getById(UUID branchId) {
        return ResponseEntity.ok(branchService.getById(branchId));
    }

    @Override
    public ResponseEntity<Page<ShortBranchDto>> getForParent(UUID branchId,
                                                             @ParameterObject BranchParentFilterDto filter,
                                                             @ParameterObject Pageable pageable) {
        BranchParentFilterDto effectiveFilter =
                new BranchParentFilterDto(filter.name(), filter.area(), filter.address(), List.of(branchId));

        Page<ShortBranchDto> result = branchService.getForParent(effectiveFilter, pageable);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Page<ShortBranchDto>> getByFilters(@ParameterObject BranchFilterDto filter,
                                                             @ParameterObject Pageable pageable) {
        Page<ShortBranchDto> result = branchService.getByFilters(filter, pageable);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<BranchDto> create(CreateBranchDto dto) {
        BranchDto created = branchService.create(dto);

        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Override
    public ResponseEntity<BranchDto> update(UUID branchId, UpdateBranchDto dto) {
        return ResponseEntity.ok(branchService.update(branchId, dto));
    }

    @Override
    public ResponseEntity<Void> delete(UUID branchId) {
        branchService.delete(branchId);
        return ResponseEntity.noContent().build();
    }
}
