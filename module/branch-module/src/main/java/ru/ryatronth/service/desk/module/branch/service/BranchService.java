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
    public Page<ShortBranchDto> getByFilters(BranchFilterDto filter, Pageable pageable) {
        Specification<Branch> spec = new BranchSpecificationBuilder()
                .area(filter.getArea())
                .name(filter.getName())
                .address(filter.getAddress())
                .build();

        Page<Branch> page = branchRepository.findAll(spec, pageable);
        return page.map(branchMapper::toShortDto);
    }


    @Transactional
    public BranchDto create(CreateBranchDto dto) {
        Branch parent = null;
        if (dto.getParentId() != null) {
            parent = branchRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent branch not found: " + dto.getParentId()));
        }

        BranchType type = branchTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException("BranchType not found: " + dto.getTypeId()));

        BranchCode branchCode = branchCodeRepository.findById(dto.getCodeId())
                .orElseThrow(() -> new EntityNotFoundException("BranchCode not found: " + dto.getCodeId()));

        Branch branch =
                Branch.builder().parent(parent).type(type).code(branchCode).name(dto.getName()).area(dto.getArea())
                        .address(dto.getAddress()).build();

        Branch saved = branchRepository.save(branch);
        return branchMapper.toDto(saved);
    }

    @Transactional
    public BranchDto update(UUID id, UpdateBranchDto dto) {
        Branch branch =
                branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Branch not found: " + id));

        if (dto.getParentId() != null) {
            Branch parent = branchRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent branch not found: " + dto.getParentId()));
            branch.setParent(parent);
        }

        if (dto.getTypeId() != null) {
            BranchType type = branchTypeRepository.findById(dto.getTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("BranchType not found: " + dto.getTypeId()));
            branch.setType(type);
        }

        if (dto.getCodeId() != null) {
            BranchCode branchCode = branchCodeRepository.findById(dto.getCodeId())
                    .orElseThrow(() -> new EntityNotFoundException("BranchCode not found: " + dto.getCodeId()));
            branch.setCode(branchCode);
        }

        if (dto.getName() != null) {
            branch.setName(dto.getName());
        }

        if (dto.getArea() != null) {
            branch.setArea(dto.getArea());
        }

        if (dto.getAddress() != null) {
            branch.setAddress(dto.getAddress());
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
