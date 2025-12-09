package ru.ryatronth.service.desk.module.branch.api.branch;

import java.net.URI;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ru.ryatronth.service.desk.dto.branch.*;
import ru.ryatronth.service.desk.module.branch.api.branch.filter.BranchFilterDto;
import ru.ryatronth.service.desk.module.branch.service.BranchService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BranchController implements BranchApiV1 {

    private final BranchService branchService;

    @Override
    public ResponseEntity<Page<BranchCodeDto>> getBranchCodes(Pageable pageable) {
        return ResponseEntity.ok(branchService.getCodes(pageable));
    }

    @Override
    public ResponseEntity<BranchDto> getById(UUID branchId) {
        return ResponseEntity.ok(branchService.getById(branchId));
    }

    @Override
    public ResponseEntity<Page<ShortBranchDto>> getByFilters(String name, String area, String address,
                                                             Pageable pageable) {
        BranchFilterDto filter = BranchFilterDto.builder()
                .name(name)
                .area(area)
                .address(address)
                .build();

        Page<ShortBranchDto> result = branchService.getByFilters(filter, pageable);
        return ResponseEntity.ok(result);
    }


    @Override
    public ResponseEntity<BranchDto> create(CreateBranchDto dto) {
        BranchDto created = branchService.create(dto);

        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(location).body(created);
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
