package com.example.springsecuritydemo3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "everyone can see this";
    }

}
