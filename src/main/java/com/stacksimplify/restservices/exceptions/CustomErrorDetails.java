package com.stacksimplify.restservices.exceptions;

import java.util.Date;

//Simple custom error details bean
public class CustomErrorDetails {

	private Date timestamp;
	private String message;
	private String errordetails;

	// Fields Constructors
	public CustomErrorDetails(Date timestamp, String message, String errordetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}

	// Getter Method
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getErrordetails() {
		return errordetails;
	}

}
