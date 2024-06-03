package com.example.todo.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import com.example.todo.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
public class HomeController {

    @Value("${app.developer.name}")
    private String appDeveloper;

    @Value("${app.developer.email}")
    private String appDeveloperEmail;

    @Value("${theme}")
    private String theme;

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/")
    public ModelAndView index() {
        log.info("THEME: {}", theme);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("appDeveloper", appDeveloper);
        modelAndView.addObject("appDeveloperEmail", appDeveloperEmail);
        modelAndView.addObject("theme", theme);
        modelAndView.addObject("todoItems", todoItemService.getAll());
        return modelAndView;
    }

}
