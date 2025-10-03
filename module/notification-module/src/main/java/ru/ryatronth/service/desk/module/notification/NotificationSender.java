package ru.ryatronth.service.desk.module.notification;

import ru.ryatronth.service.desk.data.notification.Notification;

public interface NotificationSender<T extends Notification> {
    void send(T notification);
}
