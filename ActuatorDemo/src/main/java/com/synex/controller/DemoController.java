package com.synex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/")
    public String home() {
    	
    	/*
    	 http://localhost:8282/actuator/health
    	 http://localhost:8282/actuator/info
    	 http://localhost:8282/actuator/metrics
    	 
    	 */
        return "Hello, Spring Boot Actuator!";
    }
}
