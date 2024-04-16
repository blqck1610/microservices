package com.notification;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private Integer customerId;
    private String customerEmail;
    private String message;

}
