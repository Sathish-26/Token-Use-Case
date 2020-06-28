package com.altimetrik.token.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TokenExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleMasterException(BaseException ex, WebRequest req) {
		return handleCustomizedException(ex, req);
	}

	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<Object> handleBaseException(BaseException ex, WebRequest req) {
		return handleCustomizedException(ex, req);
	}

	private ResponseEntity<Object> handleCustomizedException(BaseException ex, WebRequest req) {
		String uniqueErrorId = ex.getId();
		HttpStatus httpStatus = ex.getHttpStatus();
		String message = ex.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(uniqueErrorId, httpStatus, message);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>(errorResponse, headers, ex.getHttpStatus());
	}
}
