package com.synex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.synex.jms.EmailReceiver;
import com.synex.model.Email;

@SpringBootApplication
public class JmsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsDemoApplication.class, args);
		
		//EmailReceiver email = new EmailReceiver();
		//email.receiveMessage(new Email("ankit","hello"));
	}

}
