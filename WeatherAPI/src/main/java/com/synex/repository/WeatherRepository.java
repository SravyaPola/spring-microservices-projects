package com.synex.repository;

import com.synex.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	List<Weather> findByDate(String date);

	@Query("SELECT w FROM Weather w where LOWER(w.city) IN :cities ")
	List<Weather> findByCityInIgnoreCase(@Param("cities") List<String> cities);

	@Query("SELECT w FROM Weather w WHERE w.date=:date AND LOWER(w.city) IN :cities ")
	List<Weather> findByDateAndCityIgnoreCase(@Param("date") String date, @Param("cities") List<String> cities);
}