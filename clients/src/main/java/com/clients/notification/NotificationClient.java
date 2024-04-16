package com.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "NOTIFICATION"

)
public interface NotificationClient {
    @PostMapping(value = "api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}
