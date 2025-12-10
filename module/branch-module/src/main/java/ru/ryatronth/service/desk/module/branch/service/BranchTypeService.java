package ru.ryatronth.service.desk.module.branch.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.ryatronth.service.desk.data.branch.model.type.BranchType;
import ru.ryatronth.service.desk.data.branch.model.type.BranchTypeRepository;
import ru.ryatronth.service.desk.dto.type.BranchTypeDto;
import ru.ryatronth.service.desk.dto.type.CreateBranchTypeDto;
import ru.ryatronth.service.desk.dto.type.UpdateBranchTypeDto;
import ru.ryatronth.service.desk.module.branch.mapper.BranchTypeMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BranchTypeService {

    private final BranchTypeRepository branchTypeRepository;
    private final BranchTypeMapper branchTypeMapper;

    @Transactional(readOnly = true)
    public Page<BranchTypeDto> getTypes(Pageable pageable) {
        return branchTypeRepository.findAll(pageable).map(branchTypeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public BranchTypeDto getById(UUID id) {
        BranchType type = branchTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BranchType not found: " + id));
        return branchTypeMapper.toDto(type);
    }

    @Transactional
    public BranchTypeDto create(CreateBranchTypeDto dto) {
        BranchType type = BranchType.builder().name(dto.name()).build();

        BranchType saved = branchTypeRepository.save(type);
        return branchTypeMapper.toDto(saved);
    }

    @Transactional
    public BranchTypeDto update(UUID id, UpdateBranchTypeDto dto) {
        BranchType type = branchTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BranchType not found: " + id));

        if (dto.name() != null) {
            type.setName(dto.name());
        }

        BranchType saved = branchTypeRepository.save(type);
        return branchTypeMapper.toDto(saved);
    }

    @Transactional
    public void delete(UUID id) {
        if (!branchTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("BranchType not found: " + id);
        }
        branchTypeRepository.deleteById(id);
    }
}
