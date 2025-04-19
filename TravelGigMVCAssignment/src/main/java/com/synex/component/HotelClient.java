package com.synex.component;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.synex.model.Hotel;

@Component
public class HotelClient {

	private static final String my_post_url = "http://localhost:8383/addHotel";

	public Hotel myPostClient(Hotel hotel) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Hotel> responseEntity = restTemplate.postForEntity(my_post_url, hotel, Hotel.class);
		Hotel result = responseEntity.getBody();
		return result;
	}

}
