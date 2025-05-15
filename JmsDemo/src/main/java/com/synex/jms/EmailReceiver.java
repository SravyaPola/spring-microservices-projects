package com.synex.jms;

import java.util.concurrent.TimeUnit;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.synex.model.Email;

@Component
public class EmailReceiver {

    @JmsListener(destination = "queuedest", concurrency = "5-10")
    public void receiveMessage(Email email) {
    	System.out.println("Received Email: "+email+" "+Thread.currentThread());
    	
    	 
    }
}
