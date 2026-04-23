package com.atlas.userservice.exception; // package for exception 

/*
 * custom exception for invalid input or bad request 
 */
public class BadRequestException extends RuntimeException {

	/**
	 * unique version id for serialization (prevents invalid class exception)
	 */
	private static final long serialVersionUID = 8416170940799606634L;

	/*
	 * constructor to pass custom error message
	 * 
	 * @param message error message
	 */
	public BadRequestException(String message) {
		super(message); // call parent class constructor with message.

	}

}
