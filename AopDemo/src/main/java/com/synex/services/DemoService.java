package com.synex.services;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String sayHello(String name) {
        System.out.println("Hello from sayHello method!");
        return name;
    }

    public void sayGoodbye() {
        System.out.println("Goodbye from sayGoodbye method!");
    }
}
