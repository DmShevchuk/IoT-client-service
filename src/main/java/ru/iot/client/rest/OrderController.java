package ru.iot.client.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.iot.client.dto.producer.OrderEventDto;
import ru.iot.client.service.OrderService;
import ru.iot.client.service.producer.KafkaProducer;

import java.util.UUID;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducer producer;

    @PostMapping("/test")
    public void sendOrder(@RequestBody OrderEventDto order) {
        producer.sendOrderEvent(order);
    }

    @PostMapping
    public void createOrderByNotification(@RequestParam UUID notificationId) {
        orderService.createOrderByNotificationId(notificationId);
    }

}