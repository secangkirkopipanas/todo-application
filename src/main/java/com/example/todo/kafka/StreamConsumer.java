package com.example.todo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("kafka")
public class StreamConsumer {

    @KafkaListener(topics = "${app.kafka.todo.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    void todoListener(String data) {
        log.info("Received message [{}] in group1", data);
    }
}
