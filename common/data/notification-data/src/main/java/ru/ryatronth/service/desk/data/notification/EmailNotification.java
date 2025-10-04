package ru.ryatronth.service.desk.data.notification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification extends Notification {

    @Column(name = "email_sent", nullable = false)
    private boolean emailSent = false;

}
