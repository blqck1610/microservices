package com.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final  NotificationRepository notificationRepository;
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(Notification.builder()
                        .toCustomerId(notificationRequest.getCustomerId())
                        .message(notificationRequest.getMessage())
                        .toCustomerEmail(notificationRequest.getCustomerEmail())
                        .sender("blqck")
                        .sendAt(LocalDateTime.now())
                .build());
    }
}
