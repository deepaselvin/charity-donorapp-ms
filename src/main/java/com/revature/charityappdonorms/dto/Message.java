package com.revature.charityappdonorms.dto;

public class Message {
	private String messageinfo;

	public String getMessage() {
		return messageinfo;
	}

	public void setMessage(String message) {
		this.messageinfo = message;
	}

	public Message(String message) {
		super();
		this.setMessage(message);
	}
}