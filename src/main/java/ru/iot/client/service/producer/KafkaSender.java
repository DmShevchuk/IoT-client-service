package ru.iot.client.service.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.iot.client.dto.producer.OrderEventDto;

@Service
@RequiredArgsConstructor
public class KafkaSender {

    @Value("${kafka-topic.send-order}")
    private String orderTopic;

    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendOrder(OrderEventDto orderEventDto) {
        kafkaTemplate.send(orderTopic, orderEventDto.getOrderId().toString(), orderEventDto);
    }

}