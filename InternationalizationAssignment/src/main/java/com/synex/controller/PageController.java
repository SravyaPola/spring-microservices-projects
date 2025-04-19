package com.synex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

       
    @GetMapping("/home")
    public String home() {
        return "Home";
    }
    
//    @GetMapping("/")
//    public String redirectToHome(@RequestParam(value = "lang", required = false) String lang) {
//        return "redirect:/home?lang=" + (lang != null ? lang : "en");
//    }
    
    
}
