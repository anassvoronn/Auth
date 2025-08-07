package org.nastya.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;

    public void sendMessage(String topic, Map<String, String> message) {
        kafkaTemplate.send(topic, message);
    }
}