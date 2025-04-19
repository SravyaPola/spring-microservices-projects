package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.component.WeatherApiClient;

@RestController
public class TestController {

	@Autowired
	WeatherApiClient weatherApiClient;

	@RequestMapping(value = "/testGetUser/{data}", method = RequestMethod.GET)
	public String testGetUser(@PathVariable String data) {
		return weatherApiClient.testGetClient(data);
	}

	@RequestMapping(value = "/testPostUser", method = RequestMethod.POST)
	public JsonNode testPostUser(@RequestBody JsonNode node) {
		System.out.println(node.get("data"));
		return weatherApiClient.testPostClient(node);
	}

}
