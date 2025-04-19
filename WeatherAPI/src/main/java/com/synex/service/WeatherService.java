package com.synex.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Weather;
import com.synex.repository.WeatherRepository;

@Service
public class WeatherService {

	@Autowired
	WeatherRepository weatherRepository;

	public Weather createWeather(Weather weather) {
		return weatherRepository.save(weather);
	}

	public List<Weather> getWeather(String date, String city, String sort) {
		List<Weather> weathers;

		// filter applied
		if (date != null || city != null) {
			if (date != null && city != null) {
				// filter by date and city
				List<String> cities = Arrays.stream(city.split(",")).map(String::toLowerCase).toList();
				weathers = weatherRepository.findByDateAndCityIgnoreCase(date, cities);
			} else if (date != null) {
				// filter by date only
				weathers = weatherRepository.findByDate(date);
			} else {
				// filter by city only
				List<String> cities = Arrays.stream(city.split(",")).map(String::toLowerCase).toList();
				weathers = weatherRepository.findByCityInIgnoreCase(cities);
			}
			// no filter applied
		} else {
			weathers = weatherRepository.findAll();
		}
		// sorting
		Comparator<Weather> comparator = Comparator.comparing(Weather::getId);
		if (sort != null) {
			switch (sort) {
			case "date":
				// ascending
				comparator = Comparator.comparing(Weather::getDate).thenComparing(Weather::getId);
				break;
			case "-date":
				// descending
				comparator = Comparator.comparing(Weather::getDate).reversed().thenComparing(Weather::getId);
				break;
			}
		}
		weathers.sort(comparator);

		return weathers;
	}

	public Optional<Weather> getWeatherById(Long id) {
		return weatherRepository.findById(Math.toIntExact(id));
	}

}