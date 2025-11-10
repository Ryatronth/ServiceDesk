package ru.ryatronth.service.desk.module.branch;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ryatronth.service.desk.data.branch.model.branch.Branch;
import ru.ryatronth.service.desk.data.branch.model.branch.BranchRepository;
import ru.ryatronth.service.desk.dto.branch.AddEmployeeDto;
import ru.ryatronth.service.desk.dto.branch.BranchDto;
import ru.ryatronth.service.desk.dto.branch.BranchEmployeeDto;
import ru.ryatronth.service.desk.dto.branch.CreateBranchDto;
import ru.ryatronth.service.desk.dto.branch.UpdateBranchDto;
import ru.ryatronth.service.desk.module.branch.mapper.BranchContactMapper;
import ru.ryatronth.service.desk.module.branch.mapper.BranchEmployeeMapper;
import ru.ryatronth.service.desk.module.branch.mapper.BranchMapper;
import ru.ryatronth.service.desk.module.branch.mapper.BranchTypeMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final BranchEmployeeMapper employeeMapper;
    private final BranchContactMapper contactMapper;
    private final BranchTypeMapper branchTypeClient;

    public BranchDto getBranchById(UUID id) {
        Branch entity = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));

        BranchDto parent = entity.getParentId() != null
                ? getBranch(entity.getParentId())
                : null;

        var managerUser = userClient.getUserById(entity.getManagerId());
        var managerDto = employeeMapper.toDto(null, managerUser);

        var type = branchTypeClient.getTypeById(entity.getTypeId());
        var contacts = contactMapper.toDtoList(getContactsByBranch(entity.getId()));

        return branchMapper.toDto(entity, parent, managerDto, type, contacts);
    }

    public BranchDto create(CreateBranchDto dto) {

    }

    public BranchDto update(UpdateBranchDto dto) {

    }

    public BranchEmployeeDto addEmployee(UUID branchId, AddEmployeeDto dto) {

    }

}
