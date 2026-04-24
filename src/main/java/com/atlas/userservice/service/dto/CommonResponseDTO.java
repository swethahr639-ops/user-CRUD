package com.atlas.userservice.service.dto;

import java.time.LocalDate;

/*
 * generic response wrapper for all API's
 */
public class CommonResponseDTO<T> {
	private String message;// response message 
	private int status;// Http Status code
	private T data;// actual response data 
	private LocalDate timeStamp;// Response time
	/*
	 * Constructor
	 * 
	 * @param message
	 * @param status
	 * @param data
	 * @param timestamp
	 */
	public CommonResponseDTO(String message, int status, T data, LocalDate timeStamp) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public int getStatus() {
		return status;
	}
	public T getData() {
		return data;
	}
	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	

}
