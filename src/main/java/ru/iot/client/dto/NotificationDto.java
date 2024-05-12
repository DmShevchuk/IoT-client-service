package ru.iot.client.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class NotificationDto {

    private UUID clientId;
    private List<String> products;
    private LocalDateTime createdAt;
}
