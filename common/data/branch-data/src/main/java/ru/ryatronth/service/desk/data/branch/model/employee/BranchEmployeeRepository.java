package ru.ryatronth.service.desk.data.branch.model.employee;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchEmployeeRepository extends JpaRepository<BranchEmployee, UUID> {}
