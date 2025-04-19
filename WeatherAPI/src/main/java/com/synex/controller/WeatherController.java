package com.synex.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.synex.domain.Weather;
import com.synex.service.WeatherService;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

	@Autowired
	WeatherService weatherService;

	@PostMapping
	public ResponseEntity<Weather> createWeather(@RequestBody Weather weather) {
		Weather createdWeather = weatherService.createWeather(weather);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdWeather);
	}

	@GetMapping
	public ResponseEntity<List<Weather>> getWeather(@RequestParam(required = false) String date,
			@RequestParam(required = false) String city, @RequestParam(required = false) String sort) {
		List<Weather> weathers = weatherService.getWeather(date, city, sort);
		return ResponseEntity.status(HttpStatus.OK).body(weathers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Weather> getWeatherById(@PathVariable Long id) {
		Optional<Weather> weather = weatherService.getWeatherById(id);
		return weather.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}