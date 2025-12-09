package ru.ryatronth.service.desk.module.branch.api.type;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.dto.type.BranchTypeDto;
import ru.ryatronth.service.desk.dto.type.CreateBranchTypeDto;
import ru.ryatronth.service.desk.dto.type.UpdateBranchTypeDto;
import ru.ryatronth.service.desk.module.branch.service.BranchTypeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BranchTypeController implements BranchTypeApiV1 {

    private final BranchTypeService branchTypeService;

    @Override
    public ResponseEntity<Page<BranchTypeDto>> getBranchTypes(Pageable pageable) {
        Page<BranchTypeDto> result = branchTypeService.getTypes(pageable);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<BranchTypeDto> getBranchTypeById(UUID id) {
        BranchTypeDto dto = branchTypeService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<BranchTypeDto> createBranchType(CreateBranchTypeDto dto) {
        BranchTypeDto created = branchTypeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<BranchTypeDto> updateBranchType(UUID id, UpdateBranchTypeDto dto) {
        BranchTypeDto updated = branchTypeService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> deleteBranchType(UUID id) {
        branchTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
