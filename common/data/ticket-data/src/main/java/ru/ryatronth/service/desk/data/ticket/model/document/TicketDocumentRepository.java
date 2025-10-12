package ru.ryatronth.service.desk.data.ticket.model.document;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDocumentRepository extends JpaRepository<TicketDocument, UUID> {}
