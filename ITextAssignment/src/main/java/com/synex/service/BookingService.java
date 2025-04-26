package com.synex.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Booking;
import com.synex.domain.BookingRules;
import com.synex.repository.MyRepository;
import jakarta.mail.MessagingException;

@Service
public class BookingService {

	@Autowired
	MyRepository myRepository;

	public String generatePdfAndSendEmail(Booking booking, List<BookingRules> bookingRules, String pdfSavePath)
			throws IOException, MessagingException {
		return myRepository.generatePdfAndSendEmail(booking, bookingRules, pdfSavePath);
	}
}
