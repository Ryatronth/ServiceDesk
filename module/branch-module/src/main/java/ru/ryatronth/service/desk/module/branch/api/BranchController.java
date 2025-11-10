package ru.ryatronth.service.desk.module.branch.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ryatronth.service.desk.dto.branch.*;
import ru.ryatronth.service.desk.module.branch.BranchService;

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
        return null;
    }

    @Override
    public ResponseEntity<BranchDto> create(CreateBranchDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<BranchDto> update(UUID branchId, UpdateBranchDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(UUID branchId) {
        return null;
    }

    @Override
    public ResponseEntity<Page<BranchEmployeeDto>> getEmployees(UUID branchId, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<BranchEmployeeDto> addEmployee(UUID branchId, AddEmployeeDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<BranchEmployeeDto> updateEmployee(UUID branchId, UUID employeeId, AddEmployeeDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(UUID branchId, UUID employeeId) {
        return null;
    }

    @Override
    public ResponseEntity<Page<BranchEmployeeCategoryDto>> getEmployeeCategories(UUID branchId, UUID employeeId, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<BranchEmployeeCategoryDto> assignEmployeeCategory(UUID branchId, UUID employeeId, AssignEmployeeCategoryDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeCategory(UUID branchId, UUID employeeId) {
        return null;
    }
}
