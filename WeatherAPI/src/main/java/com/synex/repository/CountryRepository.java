package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synex.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	public Country findByListCity_NameIgnoreCase(String city);

	// Conventions
	public Country findByNameAndCapital(String name, String capital);

	// JPQL
	@Query("SELECT c FROM Country c where UPPER(c.capital) IN :capital ")
	public List<Country> findByCapitalIgnoreCase(@Param("capital") List<String> capital);

	// Native DB Query
	@NativeQuery("select * from country where capital != :capital")
	public List<Country> findByCapitalNot(@Param("capital") String capital);

}