package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Booking;
import com.synex.domain.BookingRules;
import com.synex.repository.BookingRepository;
import com.synex.repository.RuleRepository;
import com.synex.service.BookingService;

@RestController
public class BookingController {

	@Value("${pdf.save.path}")
	private String pdfSavePath;

	@Autowired
	BookingService bookingService;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	RuleRepository ruleRepository;

	@GetMapping("/generateAndSendPdf/{customerName}")
	public ResponseEntity<String> generateAndSendPdf(@PathVariable String customerName) {
		Booking booking = bookingRepository.findByCustomerName(customerName);
		System.out.println(booking.getCustomerName());
		List<BookingRules> bookingRules = ruleRepository.findAll();
		System.out.println(bookingRules.get(1).getRuleName());
		try {
			String response = bookingService.generatePdfAndSendEmail(booking, bookingRules, pdfSavePath);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(500).body("Error generating PDF or sending email.");
		}
	}

}
