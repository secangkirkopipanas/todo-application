package com.example.todo.config;

import com.example.todo.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    ObjectMapper jsonObjectMapper() {
        return JsonUtil.getObjectMapper();
    }
}
