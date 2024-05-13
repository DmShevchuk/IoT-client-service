package ru.iot.client.service.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.iot.client.dto.consumer.NotificationEvent;
import ru.iot.client.dto.consumer.OrderStatusDto;
import ru.iot.client.service.NotificationService;
import ru.iot.client.service.OrderService;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {

    private final String orderStatusTopic = "${kafka-topic.receive-order-status}";

    private final String notificationTopic = "${kafka-topic.receive-notification}";

    private final String consumerGroupId = "${spring.kafka.consumer.group-id}";

    private final NotificationService notificationService;

    private final OrderService orderService;

    @KafkaListener(
            topics = orderStatusTopic,
            groupId = consumerGroupId,
            properties = "${kafka-consumer-properties.order-status}"
    )
    public void acceptOrderStatus(OrderStatusDto orderEvent) {
        log.info("Обновление статуса заказа {}", orderEvent);
        orderService.updateOrder(orderEvent);
    }


    @KafkaListener(
            topics = notificationTopic,
            groupId = consumerGroupId,
            properties = "${kafka-consumer-properties.notification}"
    )
    public void acceptNotification(NotificationEvent notificationEvent) {
        log.info("Уведомление получено {}", notificationEvent);
        notificationService.save(notificationEvent);
    }

}
