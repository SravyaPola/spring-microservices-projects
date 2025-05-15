package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.jms.EmailSender;
import com.synex.model.Email;

@RestController
public class JmsController {
	
	@Autowired
	EmailSender emailSender;

	@RequestMapping(value = "/testJms", method = RequestMethod.GET)
	public String testJms() {
		/*
		Email email1 = new Email("Ankit","Hello Ankit");
		Email email2 = new Email("Nitn","Hello Nitin");
		Email email3 = new Email("Sachin","Hello Sachin");
		Email email4 = new Email("Mark","Hello Mark");
		Email email5 = new Email("Danny","Hello Danny");
		Email email6 = new Email("Gaurav","Hello Gaurav");
		Email email7 = new Email("Mosumi","Hello Mousmi");
		
		emailSender.sendEmail(email1);
		emailSender.sendEmail(email2);
		emailSender.sendEmail(email3);
		emailSender.sendEmail(email4);
		emailSender.sendEmail(email5);
		emailSender.sendEmail(email6);
		emailSender.sendEmail(email7);
		*/
		
		for(int i=0;i<100000;i++) {
			emailSender.sendEmail(new Email("Name-"+i,"Hello "+i));
		}
		
		return "sucess";
	}
	
}
