package com.revature.charityappdonorms.dto;

public class Message {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message(String message) {
		super();
		this.setMessage(message);
	}
}