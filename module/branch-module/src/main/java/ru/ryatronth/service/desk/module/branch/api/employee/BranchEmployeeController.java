package ru.ryatronth.service.desk.module.branch.api.employee;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.dto.employee.AssignEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeCategoryDto;
import ru.ryatronth.service.desk.dto.employee.BranchEmployeeDto;
import ru.ryatronth.service.desk.module.branch.service.BranchEmployeeService;
import ru.ryatronth.service.desk.module.persona.UserFilterDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BranchEmployeeController implements BranchEmployeeApiV1 {

    private final BranchEmployeeService branchEmployeeService;

    @Override
    public ResponseEntity<Page<BranchEmployeeDto>> getEmployees(String firstName, String lastName, String patronymic,
                                                                String email, String phone, String workplace,
                                                                UUID branchId, Pageable pageable) {
        UserFilterDto filter = UserFilterDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .patronymic(patronymic)
                .email(email)
                .phone(phone)
                .workplace(workplace)
                .build();

        Page<BranchEmployeeDto> page = branchEmployeeService.getEmployees(branchId, filter, pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<BranchEmployeeCategoryDto> assignEmployeeCategory(UUID branchId, UUID employeeId,
                                                                            AssignEmployeeCategoryDto dto) {

        BranchEmployeeCategoryDto result = branchEmployeeService.assignEmployeeCategory(branchId, employeeId, dto);

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeCategory(UUID branchId, UUID employeeId, UUID categoryId) {
        branchEmployeeService.deleteEmployeeCategories(branchId, employeeId, categoryId);
        return ResponseEntity.noContent().build();
    }

}
