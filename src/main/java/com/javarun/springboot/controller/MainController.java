package com.javarun.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String populateTable(Model model) {
        String name = "John Doe";
        String email = "john@example.com";
        int age = 30;

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("age", age);
        return "index";
    }


}
