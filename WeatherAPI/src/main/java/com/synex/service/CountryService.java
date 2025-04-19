package com.synex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Country;
import com.synex.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;

	public Country createCountry(Country country) {
		return countryRepository.save(country);
	}

	public Country findByCityName(String city) {
		return countryRepository.findByListCity_NameIgnoreCase(city);
	}

	public Country findByBothNameCapital(String name, String capital) {
		return countryRepository.findByNameAndCapital(name, capital);
	}

	public List<Country> findByCapitalIgnoreCase(List<String> capital) {
		List<String> upperCaseCapitals = capital.stream().map(String::toUpperCase).collect(Collectors.toList());
		return countryRepository.findByCapitalIgnoreCase(upperCaseCapitals);

	}

	public List<Country> findByCapitalNot(String capital) {
		System.out.println(capital);
		List<Country> listCapital = countryRepository.findByCapitalNot(capital);
		System.out.println(listCapital);
		return listCapital;
	}
}