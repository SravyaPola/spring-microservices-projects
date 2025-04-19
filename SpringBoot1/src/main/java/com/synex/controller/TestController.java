package com.synex.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class TestController {
	
	
	//Access like this -- http://localhost:8282/api/resource/test/sravya
	@RequestMapping(value = "/test/{path}", method = RequestMethod.GET)
	public String test(@PathVariable String path) {
		return "test "+path;
	}
	
	//Access like this -- http://localhost:8282/api/resource/welcome?name=sravya
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(@RequestParam String name) {
		return "welcome "+name;
	}
	
}