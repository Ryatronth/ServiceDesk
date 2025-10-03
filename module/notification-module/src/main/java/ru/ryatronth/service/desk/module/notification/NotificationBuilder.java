package ru.ryatronth.service.desk.module.notification;

import java.util.UUID;
import ru.ryatronth.service.desk.data.notification.Notification;


public interface NotificationBuilder<T extends Notification, V> {
    T buildNotification(UUID userId, V payload);
}
