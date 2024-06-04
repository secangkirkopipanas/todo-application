package com.example.todo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    ObjectMapper jsonObjectMapper;

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> result = Map.of("message", "Hello World!");
        try {
            log.info("Return: ", jsonObjectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @GetMapping("/hello/{name}")
    public Map<String, String> hello(@PathVariable String name) {
        Map<String, String> result = Map.of("message", "Hello, " + name + "!");
        try {
            log.info("Return: ", jsonObjectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @GetMapping("/millis")
    public Map<String, Long> date() {
        Map<String, Long> result = Map.of("date", System.currentTimeMillis());
        try {
            log.info("Return: ", jsonObjectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
