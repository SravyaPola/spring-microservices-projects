package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.synex.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	Booking findByCustomerName(String customerName);

}