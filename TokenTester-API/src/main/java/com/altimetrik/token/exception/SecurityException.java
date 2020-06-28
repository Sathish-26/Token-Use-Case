package com.altimetrik.token.exception;

import org.springframework.http.HttpStatus;

public class SecurityException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3079107689526398505L;

	public SecurityException(String appCode, String errorMessage, HttpStatus httpStatus) {
		super(appCode, errorMessage, httpStatus, null);
	}
}
