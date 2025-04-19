package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Books;
import com.synex.service.BooksService;

@RestController
public class BooksController {

	@Autowired
	BooksService booksService;

	@RequestMapping(value = "/saveBooks", method = RequestMethod.POST)
	public Books saveBooks(@RequestBody Books books) {
		return booksService.saveBooks(books);

	}

	@RequestMapping(value = "/getBooks", method = RequestMethod.GET)
	public List<Books> getBooks() {
		return booksService.getBooks();

	}

	@RequestMapping(value = "/getBooksByAuthor/{author}", method = RequestMethod.GET)
	public List<Books> getItemByAuthor(@PathVariable String author) {
		return booksService.getBooksByAuthor(author);
	}

	@RequestMapping(value = "/getBooksByName/{name}", method = RequestMethod.GET)
	public Books getBooksByName(@PathVariable String name) {
		return booksService.getBooksByName(name);
	}

	@RequestMapping(value = "/updateBooks", method = RequestMethod.PUT)
	public Books updateBooks(@RequestBody Books books) {
		return booksService.saveBooks(books);

	}

}