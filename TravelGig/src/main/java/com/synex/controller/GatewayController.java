package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.component.HotelClient;

@RestController
public class GatewayController {

	@Autowired
	HotelClient hotelClient;

	@PostMapping("/myPostHotel")
	public JsonNode myPostHotel(@RequestBody JsonNode node) {
		System.out.println(node.get("hotelname").asText());
		return hotelClient.myPostClient(node);
	}

}
