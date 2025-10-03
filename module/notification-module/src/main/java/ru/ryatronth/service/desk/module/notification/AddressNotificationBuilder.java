package ru.ryatronth.service.desk.module.notification;

import java.util.UUID;
import ru.ryatronth.service.desk.data.notification.AddressNotification;
import ru.ryatronth.service.desk.dto.notification.AddressNotificationDto;

public class AddressNotificationBuilder implements NotificationBuilder<AddressNotification, AddressNotificationDto> {
    @Override
    public AddressNotification buildNotification(UUID userId, AddressNotificationDto payload) {
        return null;
    }
}
