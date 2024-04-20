package com.notification;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final  NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        //todo: twilio or firebase send message
        notificationRepository.save(Notification.builder()
                        .toCustomerId(notificationRequest.getCustomerId())
                        .message(notificationRequest.getMessage())
                        .toCustomerEmail(notificationRequest.getCustomerEmail())
                        .sender("blqck")
                        .sendAt(LocalDateTime.now())
                .build());
    }
}
