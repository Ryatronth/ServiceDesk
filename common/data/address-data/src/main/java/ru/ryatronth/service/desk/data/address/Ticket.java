package ru.ryatronth.service.desk.data.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
@Table(name = "ticket")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @TicketNumber
    @Column(name = "ticket_number", unique = true, nullable = false, updatable = false)
    private String ticketNumber;

    // Категория
    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "custom_priority", nullable = false)
    private TicketPriority customPriority;

    @Column(name = "deadline")
    private Instant deadline;

    // Базовые поля
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TicketStatus status;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "problem", nullable = false)
    private String problem;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "solved_by", nullable = false)
    private UUID solvedBy;

    @Column(name = "assigned_to", nullable = false)
    private UUID assignedTo;

    @Column(name = "spent_time_in_minutes", nullable = false)
    private Integer spentTimeInMinutes;

    @Column(name = "spent_money", nullable = false)
    private BigDecimal spentMoney;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Instant getEffectiveDeadline(TicketCategory category) {
        if (deadline != null) {
            return deadline;
        }

        if (category != null && category.getBaseDeadline() != null) {
            return createdAt.plus(category.getBaseDeadline().toHours(), ChronoUnit.HOURS);
        }

        return null;
    }

    public TicketPriority getEffectivePriority(TicketCategory category) {
        if (customPriority != null) {
            return customPriority;
        }

        return category != null ? category.getPriority() : null;
    }

}
