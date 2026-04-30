package com.atlas.userservice.exception.handler;

import java.time.LocalDateTime;
import com.atlas.userservice.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atlas.userservice.exception.BadRequestException;
import com.atlas.userservice.exception.ErrorResponse;
import com.atlas.userservice.exception.InvalidUserException;
import com.atlas.userservice.exception.UserNotFoundException;

/*
 * Global exception Handler for Handling All Application exception 
 */
@ControllerAdvice // Enables Global Exception Handling
public class GlobalExceptionHandler {

	private final UserEntityRepository userEntityRepository;

	GlobalExceptionHandler(UserEntityRepository userEntityRepository) {
		this.userEntityRepository = userEntityRepository;
	}

	/*
	 * handle bad request
	 * 
	 * @param ex the exception object contains error detail
	 * 
	 * @return ResponseEntity containing ErrorResponse and HttpStatus 400
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
		ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());// create
																														// error
																														// response
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);// return response with status 400
	}

	/*
	 * handles userNotFoundException
	 * 
	 * @param ex the exception object contains error detail
	 * 
	 * @return ResponseEntity containing ErrorResponse and HttpStatus 404
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // return response with status 404
	}

	/*
	 * handles all uncatch exceptions(fallBackexcEptionhandleer)
	 * 
	 * @param ex the generic exception object
	 * 
	 * @return ResponseEntity containing generic error message HttpStatus 500
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
		ErrorResponse error = new ErrorResponse("Somethin went wrong.please try again later"+ex,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // return response with status 500
	}
	
	/*
	 * handles all Invalid exceptions 
	 */
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUser(InvalidUserException ex)
	{
		ErrorResponse error= new ErrorResponse(ex.getMessage(),  HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
	}

}
