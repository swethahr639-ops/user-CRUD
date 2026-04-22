package com.atlas.userservice.exception;

import java.time.LocalDateTime;
/*
 * Standard error response format
 */

public class ErrorResponse {
	private String message;// error message
	private int status;// Http status code error
	private LocalDateTime timstamp; // time of error
	/*
	 * constructor to initialize all fields
	 * 
	 * @param message
	 * 
	 * @param status
	 * 
	 * @param timestamp
	 */

	public ErrorResponse(String message, int status, LocalDateTime timstamp) {
		super();
		this.message = message;
		this.status = status;
		this.timstamp = timstamp;
	}

	// getter for message
	public String getMessage() {
		return message;
	}

	// getter for status
	public int getStatus() {
		return status;
	}

	// getter for time stamp
	public LocalDateTime getTimstamp() {
		return timstamp;
	}

}
