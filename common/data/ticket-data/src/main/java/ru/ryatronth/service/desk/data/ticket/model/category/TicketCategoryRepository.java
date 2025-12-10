package ru.ryatronth.service.desk.data.ticket.model.category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCategoryRepository
        extends JpaRepository<TicketCategory, UUID>, JpaSpecificationExecutor<TicketCategory> {
}
