package com.atlas.userservice.exception;

/*
 * custom exception thrown hen a user is not found 
 */
public class UserNotFoundException extends RuntimeException {
	/**
	 * unique version for serialization prevents invalid class exception 
	 */
	private static final long serialVersionUID = -6085681254679423529L;
	
	/*
	 * Constructor to pass custom error message
	 * @param message error message  
	 */
	public UserNotFoundException(String message) {
		super(message); // call parent class constructor 
	}

}
