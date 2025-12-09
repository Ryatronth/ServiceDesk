package ru.ryatronth.service.desk.data.branch.model.branch;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID>, JpaSpecificationExecutor<Branch> {

    @EntityGraph(attributePaths = "type, parent, code")
    @Query("""
            FROM Branch b
            WHERE b.id = :id
            """)
    Optional<Branch> findFetchTypeAndParentAndCode(@Param("id") UUID id);

}
