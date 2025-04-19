package com.synex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Country;

import com.synex.service.CountryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class CountryController {

	@Autowired
	CountryService countryService;

	@RequestMapping(value = "/createCountry")
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {
		Country createdCountry = countryService.createCountry(country);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
	}

	@RequestMapping(value = "/findByCity/{city}")
	public Country findByCity(@PathVariable String city) {
		return countryService.findByCityName(city);

	}

	@RequestMapping(value = "/findByBoth/{name}/{capital}")// country name
	public Country findByCity(@PathVariable String name, @PathVariable String capital) {
		return countryService.findByBothNameCapital(name, capital);

	}

	@RequestMapping(value = "/findByCapital")
	public List<Country> findByCapital() {
		List<String> listCapital = new ArrayList<String>();
		listCapital.add("NewDelhi");
		listCapital.add("DC");
		System.out.println(listCapital);
		return countryService.findByCapitalIgnoreCase(listCapital);

	}

	@RequestMapping(value = "/findByCapitalNot/{capital}")
	public List<Country> findByCapitalNot(@PathVariable String capital) {
		return countryService.findByCapitalNot(capital);

	}

	@RequestMapping(value = "/testGet/{data}", method = RequestMethod.GET)
	public String testGet(@PathVariable String data) {
		return "Welcome I am Weather API " + data;

	}

	@RequestMapping(value = "/testPost",method = RequestMethod.POST)
	public JsonNode testPost(@RequestBody JsonNode node) {
		((ObjectNode) node).put("age",40);
		System.out.println(node.get("data"));
		return node;
		
	}

}