package ru.ryatronth.service.desk.data.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryatronth.service.desk.data.address.number.TicketNumber;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

@Audited
@Table(name = "address")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @TicketNumber
    @Column(name = "ticket_number", unique = true, nullable = false, updatable = false)
    private String ticketNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private AddressPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AddressStatus status;

    @Column(name = "deadline")
    private Instant deadline;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "problem", nullable = false)
    private String problem;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "assigned_to", nullable = false)
    private UUID assignedTo;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}
