package ru.ryatronth.service.desk.data.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "ticket_statistics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketStatistic {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "period_start", nullable = false)
    private Instant periodStart;

    @Column(name = "period_end", nullable = false)
    private Instant periodEnd;

    @Column(name = "total_count", nullable = false)
    private int totalCount;

    @Column(name = "completed_count", nullable = false)
    private int completedCount;

    @Column(name = "cancelled_count", nullable = false)
    private int cancelledCount;

    @Column(name = "avg_processing_time_sec")
    private Long avgProcessingTimeSec;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public double getCompletionRate() {
        return totalCount > 0 ? (double) completedCount / totalCount : 0.0;
    }

    public double getCancellationRate() {
        return totalCount > 0 ? (double) cancelledCount / totalCount : 0.0;
    }

    public String getFormattedAverageTime() {
        if (avgProcessingTimeSec == null) return "-";
        long hours = avgProcessingTimeSec / 3600;
        long minutes = (avgProcessingTimeSec % 3600) / 60;
        return String.format("%02dh %02dm", hours, minutes);
    }

}
