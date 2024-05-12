package ru.iot.client.dto.consumer;


import lombok.Data;
import ru.iot.client.domain.OrderStatus;

import java.util.UUID;

@Data
public class OrderStatusDto {

    private UUID orderId;
    private OrderStatus status;
    private String description;

}
