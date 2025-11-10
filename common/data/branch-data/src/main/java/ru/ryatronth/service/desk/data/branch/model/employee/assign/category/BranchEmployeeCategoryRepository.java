package ru.ryatronth.service.desk.data.branch.model.employee.assign.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchEmployeeCategoryRepository extends JpaRepository<BranchEmployeeCategory, UUID> {}
