package com.synex.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.synex.model.Email;

@Component
public class EmailSender {
    private final JmsTemplate jmsTemplate;

    public EmailSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendEmail(Email email) {
        jmsTemplate.convertAndSend("queuedest", email);
    }
}