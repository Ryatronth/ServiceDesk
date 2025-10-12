package ru.ryatronth.service.desk.data.branch.model.executor;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchExecutorRepository extends JpaRepository<BranchExecutor, UUID> {}
