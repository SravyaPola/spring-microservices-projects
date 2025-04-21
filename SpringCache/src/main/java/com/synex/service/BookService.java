package com.synex.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import com.synex.pojo.Book;

@Service
public class BookService {

	private final Map<String, Book> fakeDb = new HashMap<>();

	public BookService() {
		//http://localhost:8282/books/123
		fakeDb.put("123", new Book("123", "Titanic"));
		//http://localhost:8282/books/256
		fakeDb.put("256", new Book("256", "Core"));

	}

	@Cacheable("books")
	public Book getBook(String isbn) {
		System.out.println("Fetching from DB" + isbn);
		return fakeDb.get(isbn);
	}

	@CachePut(value = "books", key = "#book.isbn")
	public Book updateBook(Book book) {

		System.out.println("Updating DB and Cache" + book.getIsbn());

		fakeDb.put(book.getIsbn(), book);
		return book;
	}

	@CacheEvict(value = "books", key = "#isbn")
	public void deleteBook(String isbn) {
		System.out.println("Deleting from DB and Cache" + isbn);
		fakeDb.remove(isbn);

	}

//	public Book findBookInDb(String isbn) {
//
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return new Book(isbn, "Some Book");
//
//	}
//
//	@Cacheable("books")
//	public Book getBook(String isbn) {
//
//		return findBookInDb(isbn);
//	}
}
