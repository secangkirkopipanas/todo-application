package com.example.todo.services;

import com.example.todo.models.TodoItem;
import com.example.todo.repositories.TodoItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @Autowired
    private Environment environment;

    @Value("${app.kafka.todo.topic-name}")
    private String topicName;

    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    public Iterable<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setCreatedAt(LocalDateTime.now());
        }
        todoItem.setUpdatedAt(LocalDateTime.now());
        todoItem = todoItemRepository.save(todoItem);

        if (Arrays.stream(environment.getActiveProfiles()).toList().contains("kafka")) {
            sendToTopic(topicName, todoItem);
        }

        return todoItem;
    }

    @Profile("kafka")
    public void sendToTopic(String topicName, TodoItem todoItem) {
        // Send the object into Kafka topic
        try {
            kafkaTemplate.send(topicName, jsonObjectMapper.writeValueAsString(todoItem));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }

}
