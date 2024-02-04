package com.zenyatta.challenge.inditex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheck {

    @GetMapping("/")
    public String hellothere() {
        return "Alive!";
    }

}
