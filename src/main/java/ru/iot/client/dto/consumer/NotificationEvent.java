package ru.iot.client.dto.consumer;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class NotificationEvent {

    private UUID clientId;
    private List<String> products;

}
