package com.altimetrik.token.model;

public class TokenResponse {

	private String status;

	public TokenResponse(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
