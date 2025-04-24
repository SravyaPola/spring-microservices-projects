package com.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.services.DemoService;

@RestController
public class DemoController {

	@Value("${pdf.save.path}")
	private String pdfSavePath;
	
	@Autowired
	DemoService demoService;

	@GetMapping("/generateAndSendPdf/{content}/{email}")
	public ResponseEntity<String> generateAndSendPdf(@PathVariable String content, @PathVariable String email) {
		try {
			// Call service method to generate PDF and send email
			String response = demoService.generatePdfAndSendEmail(content, email, pdfSavePath);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(500).body("Error generating PDF or sending email.");
		}
	}
	
	
	@GetMapping("/generate/{content}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable String content) {
        try {
            // Call service to generate the PDF as a byte array
            byte[] pdfBytes = demoService.generatePdf(content);

            // Set headers for PDF response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "dynamic.pdf");

            // Return PDF as response
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            // Handle exceptions and return 500 response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/generateAndSave/{content}")
    public ResponseEntity<String> generateAndSavePdf(@PathVariable String content) {
        try {
            // Call service to generate the PDF as a byte array
            String response = demoService.generateAndSavePdf(content,pdfSavePath+"/demo.pdf");

            // Set headers for PDF response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            

            // Return PDF as response
            return ResponseEntity.ok().headers(headers).body("OK");

        } catch (Exception e) {
            // Handle exceptions and return 500 response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
