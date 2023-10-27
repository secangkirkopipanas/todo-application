package com.example.todo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello World!");
    }

    @GetMapping("/hello/{name}")
    public Map<String, String> hello(@PathVariable String name) {
        return Map.of("message", "Hello, " + name + "!");
    }

    @GetMapping("/millis")
    public Map<String, Long> date() {
        return Map.of("date", System.currentTimeMillis());
    }

}
