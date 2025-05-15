package com.synex.model;

import java.io.Serializable;

public class Email implements Serializable{
	private static final long serialVersionUID = 1L;
    private String to;
    private String body;
    
    
    
    
	@Override
	public String toString() {
		return "Email [to=" + to + ", body=" + body + "]";
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Email(String to, String body) {
		super();
		this.to = to;
		this.body = body;
	}

    
}