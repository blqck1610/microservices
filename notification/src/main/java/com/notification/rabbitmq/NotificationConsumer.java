package com.notification.rabbitmq;

import com.notification.NotificationConfig;
import com.notification.NotificationRequest;
import com.notification.NotificationService;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    @Observed
    public void consumerNotification(NotificationRequest notificationRequest){
    log.info("consumer  {} from queue" , notificationRequest);
    notificationService.send(notificationRequest);
    }

}
