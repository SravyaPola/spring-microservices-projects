package com.synex.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.synex.domain.Hotel;
import com.synex.service.HotelService;

@RestController
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping("/addHotel")
	public JsonNode AddHotel(@RequestBody JsonNode node) {
		System.out.println(node.get("hotelname").asText());
		ObjectMapper objectMapper = new ObjectMapper();
		Hotel hotel = objectMapper.convertValue(node, Hotel.class);
		Hotel addedHotel = hotelService.AddHotel(hotel);
		String uniqueId = addedHotel.getId() + "_" + LocalDate.now().toString();
		((ObjectNode) node).put("uniqueId", uniqueId);
		return node;
	}
}
