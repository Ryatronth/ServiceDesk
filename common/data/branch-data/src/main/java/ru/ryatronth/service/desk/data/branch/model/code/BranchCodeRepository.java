package ru.ryatronth.service.desk.data.branch.model.code;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchCodeRepository extends JpaRepository<BranchCode, UUID> {

    Optional<BranchCode> findByCode(String code);

}
