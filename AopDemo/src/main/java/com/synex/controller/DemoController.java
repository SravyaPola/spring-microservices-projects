package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.synex.services.DemoService;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        demoService.sayHello(name);
        return "Hello endpoint called!";
    }

    @GetMapping("/goodbye")
    public String sayGoodbye() {
        demoService.sayGoodbye();
        return "Goodbye endpoint called!";
    }
}
