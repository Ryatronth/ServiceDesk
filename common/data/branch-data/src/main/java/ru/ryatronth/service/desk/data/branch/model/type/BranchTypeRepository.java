package ru.ryatronth.service.desk.data.branch.model.type;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchTypeRepository extends JpaRepository<BranchType, UUID> {}
