package com.bank.account_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/heallth")
public class HealthController {

    @GetMapping
    public Map<String, String> checkHealth() {
        return Map.of("status", "UP");
    }

}
