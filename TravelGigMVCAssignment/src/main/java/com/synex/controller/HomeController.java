package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.synex.component.HotelClient;
import com.synex.model.Hotel;

@Controller
public class HomeController {

	@Autowired
	HotelClient hotelClient;

	@GetMapping("/hotelForm")
	public String showForm(Model model) {
		model.addAttribute("hotel", new Hotel());
		return "hotel";
	}

	@PostMapping("/submitHotel")
	public String submitForm(@ModelAttribute("user") Hotel hotel, Model model) {
		Hotel savedHotel = hotelClient.myPostClient(hotel);
		model.addAttribute("hotel", savedHotel);
		return "hotelResult";
	}

}
