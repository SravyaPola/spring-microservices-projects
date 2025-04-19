package com.synex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Hotel;
import com.synex.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	HotelRepository hotelRepository;

	public Hotel AddHotel(Hotel hotel) {
		Hotel savedHotel = hotelRepository.save(hotel);
		return savedHotel;
	}

}
