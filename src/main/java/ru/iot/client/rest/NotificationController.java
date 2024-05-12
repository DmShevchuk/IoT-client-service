package ru.iot.client.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iot.client.dto.NotificationDto;
import ru.iot.client.service.NotificationService;

import java.util.UUID;

@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping
    public NotificationDto findByClientId() {
        return notificationService.findByClientId();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        notificationService.deleteById(id);
    }

}
