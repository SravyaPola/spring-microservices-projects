package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

	public List<Books> findByAuthor(String author);

	public Books findByName(String name);

}
