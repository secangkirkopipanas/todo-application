package com.example.todo.services;

import com.example.todo.models.TodoItem;
import com.example.todo.repositories.TodoItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    public Iterable<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setCreatedAt(System.currentTimeMillis());
        }
        todoItem.setUpdatedAt(System.currentTimeMillis());
        todoItem = todoItemRepository.save(todoItem);

        // Send the object into Kafka topic
        try {
            kafkaTemplate.send("todo", jsonObjectMapper.writeValueAsString(todoItem));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return todoItem;
    }

    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }

}
