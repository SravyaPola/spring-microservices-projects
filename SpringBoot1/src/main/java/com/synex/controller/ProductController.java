package com.synex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class ProductController {

	public static List<JsonNode> listProduct = new ArrayList<JsonNode>();
//Access like this -- http://localhost:8282/html/product.html
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public List<JsonNode> addProduct(@RequestBody JsonNode node) {
		listProduct.add(node);
		System.out.println(node.get("prodName"));
		return listProduct;
	}

}