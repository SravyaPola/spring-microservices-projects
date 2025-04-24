package com.core.services;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.repository.DemoRepository;
import jakarta.mail.MessagingException;

@Service
public class DemoService {

	@Autowired
	DemoRepository demoRepository;
	
	public String generatePdfAndSendEmail(String content, String email, String pdfSavePath) throws IOException, MessagingException {
        return demoRepository.generatePdfAndSendEmail(content, email, pdfSavePath);
    }
	
	public byte[] generatePdf(String content) throws IOException {
        return demoRepository.generatePdf(content);
    }
	
	public String generateAndSavePdf(String content,String path) throws IOException {
        return demoRepository.generateAndSavePdf(content,path);
    }
	
}
