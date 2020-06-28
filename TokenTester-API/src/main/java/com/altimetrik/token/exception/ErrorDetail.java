package com.altimetrik.token.exception;

public class ErrorDetail {

	private long timestamp;
	private String id;
	private String code;
	private String title;
	private int status;
	private ErrorSource errorSource;

	public ErrorDetail(long timestamp, String id, String code, String title, int status) {
		super();
		this.timestamp = timestamp;
		this.id = id;
		this.code = code;
		this.title = title;
		this.status = status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ErrorSource getErrorSource() {
		return errorSource;
	}

	public void setErrorSource(ErrorSource errorSource) {
		this.errorSource = errorSource;
	}

}
