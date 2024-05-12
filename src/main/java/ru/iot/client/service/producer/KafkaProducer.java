package ru.iot.client.service.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iot.client.dto.producer.OrderEventDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaSender kafkaSender;


    public void sendOrderEvent(OrderEventDto order) {
        kafkaSender.sendOrder(order);
        log.info("Отправка сообщения {}", order);
    }

}
