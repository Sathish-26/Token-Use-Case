package com.altimetrik.token.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private List<ErrorDetail> errors;

	public List<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetail> errors) {
		this.errors = errors;
	}

	public ErrorResponse(String uniqueErrorId, HttpStatus httpStatus, String message) {
		super();
		ErrorDetail errorDetail = new ErrorDetail(Instant.now().toEpochMilli(), uniqueErrorId, message, message,
				httpStatus.value());
		this.addError(errorDetail);
	}

	public void addError(ErrorDetail error) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(error);
	}

}
