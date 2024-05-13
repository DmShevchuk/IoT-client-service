package ru.iot.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.iot.client.domain.Notification;
import ru.iot.client.dto.NotificationDto;
import ru.iot.client.dto.consumer.NotificationEvent;
import ru.iot.client.repository.NotificationRepository;
import ru.iot.client.utils.AuthUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final OrderService orderService;


    public void save(NotificationEvent notificationEvent) {
        var notification = new Notification();
        notification.setClientId(notificationEvent.getClientId());
        notification.setProducts(notificationEvent.getProducts());
        notification.setCreatedAt(LocalDateTime.now());
        UUID saved = notificationRepository.saveAndFlush(notification).getId();
        log.info("Сохраняю уведомление {}", saved);
        orderService.createOrderByNotificationId(saved);
    }


    public NotificationDto findByClientId() {
        UUID clientId = AuthUtils.getCurrentUserId();
        List<Notification> notifications = notificationRepository.findAllByClientIdOrderByCreatedAtDesc(clientId);
        if (notifications.isEmpty()) {
            return null;
        }
        var notification = notifications.get(0);
        var dto = new NotificationDto();
        dto.setClientId(notification.getClientId());
        dto.setProducts(notification.getProducts());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }

    public void deleteById(UUID id) {
        notificationRepository.deleteById(id);
    }

}
