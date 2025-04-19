package com.synex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Books;
import com.synex.repository.BooksRepository;

@Service
public class BooksService {

	@Autowired
	BooksRepository booksRepository;

	public Books saveBooks(Books books) {

		return booksRepository.save(books);

	}

	public List<Books> getBooks() {

		return booksRepository.findAll();

	}

	public List<Books> getBooksByAuthor(String author) {

		return booksRepository.findByAuthor(author);

	}

	public Books getBooksByName(String name) {

		return booksRepository.findByName(name);

	}

}