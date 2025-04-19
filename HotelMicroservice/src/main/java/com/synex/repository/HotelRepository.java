package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.synex.domain.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
