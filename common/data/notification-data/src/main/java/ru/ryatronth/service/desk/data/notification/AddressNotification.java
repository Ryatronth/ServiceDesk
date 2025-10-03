package ru.ryatronth.service.desk.data.notification;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADDRESS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressNotification extends EmailNotification {

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "address_id", nullable = false)
    private UUID addressId;

}
