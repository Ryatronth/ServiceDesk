package ru.ryatronth.service.desk.module.branch.api.branch;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ru.ryatronth.service.desk.dto.branch.*;
import ru.ryatronth.service.desk.module.branch.service.BranchService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BranchController implements BranchApiV1 {

    private final BranchService branchService;

    @Override
    public ResponseEntity<BranchDto> getById(UUID branchId) {
        return null;
    }

    @Override
    public ResponseEntity<Page<BranchDto>> getByFilters(Pageable pageable) {
        return ResponseEntity.ok(branchService.getByFilters(pageable));
    }

    @Override
    public ResponseEntity<BranchDto> create(CreateBranchDto dto) {
        BranchDto created = branchService.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @Override
    public ResponseEntity<BranchDto> update(UUID branchId, UpdateBranchDto dto) {
        return ResponseEntity.ok(branchService.update(branchId, dto));
    }

    @Override
    public ResponseEntity<Void> delete(UUID branchId) {
        branchService.delete(branchId);
        return ResponseEntity.ok().build();
    }

}
