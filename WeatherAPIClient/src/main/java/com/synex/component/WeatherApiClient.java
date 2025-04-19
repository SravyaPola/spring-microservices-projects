package com.synex.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component // Marks this class as a Spring-managed bean.
public class WeatherApiClient {
// these values are constants and shared across all instances of the class.
	private static final String testGetUrl = "http://localhost:8282/testGet/";
	private static final String testPostUrl = "http://localhost:8282/testPost";

	public String testGetClient(String data) {
//Spring’s HTTP client used for calling REST services.
		RestTemplate restTemplate = new RestTemplate();
// Send a GET request to this URL, and return the full response with a body of type String.”
//Returns a ResponseEntity<String> which includes: a) HTTP status (like 200 OK) b) Headers c)Body (as String)

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(testGetUrl + data, String.class);
		String response = responseEntity.getBody();

		return response;
	}

//JsonNode node represents the JSON request body.
	public JsonNode testPostClient(JsonNode node) {
//Creates HTTP headers for the request.
		HttpHeaders headers = new HttpHeaders();
//Sets Content-Type to application/json so the server knows you're sending JSON data.
		headers.setContentType(MediaType.APPLICATION_JSON);
//HttpEntity wraps the request body and headers.		
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
//Sends POST with JSON body
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(testPostUrl, request, Object.class);
		Object objects = responseEntity.getBody();
//ObjectMapper: Jackson's class for converting between Java objects and JSON.
		ObjectMapper mapper = new ObjectMapper();
//convertValue: Converts the raw object response into a JsonNode.
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

}
