package com.stacksimplify.restservices.Exceptions;

import java.sql.Date;

public class CustomErrorDetails {

	private Date timestamp;
	private String message;
	private String errorDetails;
	
	public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
		super();
		this.setTimestamp(timestamp);
		this.setMessage(message);
		this.setErrorDetails(errorDetails);
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
