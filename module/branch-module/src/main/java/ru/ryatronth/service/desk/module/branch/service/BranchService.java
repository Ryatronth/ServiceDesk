package ru.ryatronth.service.desk.module.branch.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.data.branch.model.branch.Branch;
import ru.ryatronth.service.desk.data.branch.model.branch.BranchRepository;
import ru.ryatronth.service.desk.data.branch.model.branch.BranchSpecificationBuilder;
import ru.ryatronth.service.desk.data.branch.model.code.BranchCode;
import ru.ryatronth.service.desk.data.branch.model.code.BranchCodeRepository;
import ru.ryatronth.service.desk.data.branch.model.type.BranchType;
import ru.ryatronth.service.desk.data.branch.model.type.BranchTypeRepository;
import ru.ryatronth.service.desk.dto.branch.BranchCodeDto;
import ru.ryatronth.service.desk.dto.branch.BranchDto;
import ru.ryatronth.service.desk.dto.branch.CreateBranchDto;
import ru.ryatronth.service.desk.dto.branch.ShortBranchDto;
import ru.ryatronth.service.desk.dto.branch.UpdateBranchDto;
import ru.ryatronth.service.desk.module.branch.api.branch.filter.BranchFilterDto;
import ru.ryatronth.service.desk.module.branch.api.branch.filter.BranchParentFilterDto;
import ru.ryatronth.service.desk.module.branch.mapper.BranchMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchTypeRepository branchTypeRepository;
    private final BranchCodeRepository branchCodeRepository;
    private final BranchMapper branchMapper;

    public Page<BranchCodeDto> getCodes(Pageable pageable) {
        return branchCodeRepository.findAll(pageable).map(branchMapper::toDto);
    }

    @Transactional(readOnly = true)
    public BranchDto getById(UUID id) {
        Branch branch = branchRepository.findFetchTypeAndParentAndCode(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found: " + id));
        return branchMapper.toDto(branch);
    }

    @Transactional(readOnly = true)
    public Page<ShortBranchDto> getForParent(BranchParentFilterDto filter, Pageable pageable) {
        Specification<Branch> spec =
                new BranchSpecificationBuilder().name(filter.name()).area(filter.area()).address(filter.address())
                        .exclude(filter.exclude()).build();

        return branchRepository.findAll(spec, pageable).map(branchMapper::toShortDto);
    }

    @Transactional(readOnly = true)
    public Page<ShortBranchDto> getByFilters(BranchFilterDto filter, Pageable pageable) {
        Specification<Branch> spec =
                new BranchSpecificationBuilder().name(filter.name()).area(filter.area()).address(filter.address())
                        .build();

        return branchRepository.findAll(spec, pageable).map(branchMapper::toShortDto);
    }

    @Transactional
    public BranchDto create(CreateBranchDto dto) {
        Branch parent = null;
        if (dto.parentId() != null) {
            parent = branchRepository.findById(dto.parentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent branch not found: " + dto.parentId()));
        }

        BranchType type = branchTypeRepository.findById(dto.typeId())
                .orElseThrow(() -> new EntityNotFoundException("BranchType not found: " + dto.typeId()));

        BranchCode branchCode = branchCodeRepository.findById(dto.codeId())
                .orElseThrow(() -> new EntityNotFoundException("BranchCode not found: " + dto.codeId()));

        Branch branch = Branch.builder().parent(parent).type(type).code(branchCode).name(dto.name()).area(dto.area())
                .address(dto.address()).build();

        Branch saved = branchRepository.save(branch);
        return branchMapper.toDto(saved);
    }

    @Transactional
    public BranchDto update(UUID id, UpdateBranchDto dto) {
        Branch branch =
                branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Branch not found: " + id));

        if (dto.parentId() != null) {
            Branch parent = branchRepository.findById(dto.parentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent branch not found: " + dto.parentId()));
            branch.setParent(parent);
        }

        if (dto.typeId() != null) {
            BranchType type = branchTypeRepository.findById(dto.typeId())
                    .orElseThrow(() -> new EntityNotFoundException("BranchType not found: " + dto.typeId()));
            branch.setType(type);
        }

        if (dto.codeId() != null) {
            BranchCode branchCode = branchCodeRepository.findById(dto.codeId())
                    .orElseThrow(() -> new EntityNotFoundException("BranchCode not found: " + dto.codeId()));
            branch.setCode(branchCode);
        }

        if (dto.name() != null) {
            branch.setName(dto.name());
        }

        if (dto.area() != null) {
            branch.setArea(dto.area());
        }

        if (dto.address() != null) {
            branch.setAddress(dto.address());
        }

        Branch saved = branchRepository.save(branch);
        return branchMapper.toDto(saved);
    }

    @Transactional
    public void delete(UUID id) {
        if (!branchRepository.existsById(id)) {
            throw new EntityNotFoundException("Branch not found: " + id);
        }
        branchRepository.deleteById(id);
    }
}
