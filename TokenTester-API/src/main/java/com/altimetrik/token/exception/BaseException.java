package com.altimetrik.token.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private HttpStatus httpStatus;
	private String id;

	public BaseException(String appCode, String errorMessage, HttpStatus httpStatus, Throwable ex) {
		super(errorMessage, ex);
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
		this.id = UUID.randomUUID().toString();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
