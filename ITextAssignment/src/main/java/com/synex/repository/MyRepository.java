package com.synex.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.synex.domain.Booking;
import com.synex.domain.BookingRules;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Repository
public class MyRepository {

	private final JavaMailSender emailSender;

	@Autowired
	public MyRepository(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public String generatePdfAndSendEmail(Booking booking, List<BookingRules> bookingRules, String pdfSavePath)
			throws IOException, MessagingException {

		String filePath = pdfSavePath + "/booking.pdf";

		generateAndSavePdf(booking, bookingRules, filePath);

		sendEmailWithAttachment(booking.getEmailId(), filePath);

		return "PDF generated and email sent successfully!";
	}

	public String generateAndSavePdf(Booking booking, List<BookingRules> bookingRules, String filePath)
			throws IOException {
		Document document = new Document();

		FileOutputStream fileOutputStream = new FileOutputStream(filePath);

		try {
			PdfWriter.getInstance(document, fileOutputStream);

			document.open();

			document.add(new Paragraph("Here are your Hotel Booking Details"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Customer Name :" + booking.getCustomerName()));
			document.add(new Paragraph("Check-In Date :" + booking.getCheckInDate()));
			document.add(new Paragraph("Check-Out Date :" + booking.getCheckOutDate()));
			document.add(new Paragraph("Hotel Name:" + booking.getHotelName()));

			Path path = Paths.get(ClassLoader.getSystemResource("hotel.jpg").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.scaleToFit(400, 300);
			img.setAlignment(Image.ALIGN_CENTER);
			document.add(img);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Please Follow the below rules"));
			document.add(new Paragraph("\n"));
			PdfPTable table = new PdfPTable(3);
			table.addCell("Rule No.");
			table.addCell("Rule Name");
			table.addCell("Rule Description");
			ListIterator<BookingRules> it = bookingRules.listIterator();
			while (it.hasNext()) {
				BookingRules rule = it.next();
				table.addCell(new PdfPCell(new Phrase(String.valueOf(rule.getId()))));
				table.addCell(new PdfPCell(new Phrase(rule.getRuleName())));
				table.addCell(new PdfPCell(new Phrase(rule.getRuleDescription())));
			}
			document.add(table);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fileOutputStream.close();
		}

		return filePath;
	}

	public void sendEmailWithAttachment(String email, String filePath) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(email);
		helper.setSubject("Hotel Booking Details");
		

		String text1 = "Hello, Here is the Overview on the Hotel: \n\n";
		String text2 = "Our Hotel is a luxury boutique property offering panoramic "
				+ "views of the city skyline and waterfront. Just minutes from historic landmarks and entertainment"
				+ " districts, the hotel features 120 elegantly designed rooms, a rooftop pool, gourmet dining options,"
				+ " a full-service spa, and modern meeting facilities. \n\n";
		String text3 = "Please find your attached booking details below. \n\n";
		String text4 = "Thanks for booking!!";
		helper.setText(text1 + text2 + text3 + text4);

		File pdfFile = new File(filePath);
		helper.addAttachment(pdfFile.getName(), pdfFile);

		emailSender.send(message);
	}

}
