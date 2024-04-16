package com.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping
@Builder
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_id_sequence",sequenceName = "notification_id_sequence", allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_sequence")
    private Integer notificationId;
    private String message;
    private String sender;
    private LocalDateTime sendAt;
    private String toCustomerEmail;
    private Integer toCustomerId;
}
