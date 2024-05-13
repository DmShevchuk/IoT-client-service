package ru.iot.client.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.iot.client.domain.Notification;
import ru.iot.client.domain.Order;
import ru.iot.client.domain.OrderStatus;
import ru.iot.client.dto.consumer.OrderStatusDto;
import ru.iot.client.dto.producer.OrderEventDto;
import ru.iot.client.repository.NotificationRepository;
import ru.iot.client.repository.OrderRepository;
import ru.iot.client.service.producer.KafkaProducer;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final NotificationRepository notificationRepository;

    private final KafkaProducer kafkaProducer;

    public void createOrderByNotificationId(UUID notificationId) {
        log.info("Создаю заказ на основе уведомления {}", notificationId);
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(EntityNotFoundException::new);
        Order order = new Order();
        order.setClientId(notification.getClientId());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setProducts(notification.getProducts());
        UUID savedId = orderRepository.save(order).getId();
        kafkaProducer.sendOrderEvent(new OrderEventDto(order.getClientId(), savedId, order.getProducts()));
    }


    public void updateOrder(OrderStatusDto orderStatusDto) {
        Order order = orderRepository.findById(orderStatusDto.getOrderId())
                .orElseThrow(EntityNotFoundException::new);
        order.setStatus(orderStatusDto.getStatus());
        order.setDescription(orderStatusDto.getDescription());
        orderRepository.saveAndFlush(order);
    }
}
