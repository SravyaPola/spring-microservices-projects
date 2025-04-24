package com.core.repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Repository
public class DemoRepository {
	
	private final JavaMailSender emailSender;
	
	@Autowired
	public DemoRepository(JavaMailSender emailSender) {
		this.emailSender=emailSender;
	}

	public String generatePdfAndSendEmail(String content, String email, String pdfSavePath) throws IOException, MessagingException {
	    // Define the file path where PDF will be saved
	    String filePath = pdfSavePath + "/dynamic_pdf.pdf";

	    // Generate and save the PDF
	    generateAndSavePdf(content, filePath);

	    // Send the email with the generated PDF as attachment
	    sendEmailWithAttachment(email, "Dynamic PDF Report", "Please find the attached PDF.", filePath);

	    return "PDF generated and email sent successfully!";
	}



	public String generateAndSavePdf(String content, String filePath) throws IOException {
	    Document document = new Document();

	    FileOutputStream fileOutputStream = new FileOutputStream(filePath);

	    try {
	        PdfWriter.getInstance(document, fileOutputStream);

	        document.open();
	        document.add(new Paragraph("Dynamic PDF Content"));
	        document.add(new Paragraph(content));

	        document.close();
	    } catch (Exception e) {
	        System.out.println("Error:"+e);
	    } finally {
	        fileOutputStream.close();
	    }

	    return filePath;
	}





	public void sendEmailWithAttachment(String to, String subject, String text, String filePath) throws MessagingException {
	    MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    // Set email details
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);

	    // Attach the PDF file
	    File pdfFile = new File(filePath);
	    helper.addAttachment(pdfFile.getName(), pdfFile);

	    emailSender.send(message);
	}
	
	
	public byte[] generatePdf(String content) {
	    // Create a ByteArrayOutputStream to hold the PDF content
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    try {
	        // Create a new document
	        Document document = new Document();

	        // Initialize PdfWriter
	        PdfWriter.getInstance(document, byteArrayOutputStream);

	        // Open the document to write content
	        document.open();
	        document.add(new Paragraph("Dynamic PDF Content"));
	        document.add(new Paragraph(content));

	        // Close the document
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Return the generated PDF as a byte array
	    return byteArrayOutputStream.toByteArray();
	}



}
