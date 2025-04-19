package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.synex.domain.Hotel;
import com.synex.service.HotelService;

@RestController
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping("/addHotel")
	public Hotel AddHotel(@RequestBody Hotel hotel) {
		Hotel addedHotel = hotelService.AddHotel(hotel);
		return addedHotel;
	}
}
