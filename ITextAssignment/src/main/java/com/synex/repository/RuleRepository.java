package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.synex.domain.BookingRules;

@Repository
public interface RuleRepository extends JpaRepository<BookingRules, Integer> {

}
