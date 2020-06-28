package com.altimetrik.token.exception;

public class ErrorSource {

	private String pointer;

	public ErrorSource(String pointer) {
		super();
		this.pointer = normalize(pointer);
	}

	public String getPointer() {
		return pointer;
	}

	public void setPointer(String pointer) {
		this.pointer = pointer;
	}

	private String normalize(String nonStandardPointer) {
		if (nonStandardPointer == null) {
			pointer = "UNKNOWN";
		} else {
			StringBuilder stringBuilder = new StringBuilder("/");
			stringBuilder.append(pointer.replace(".", "/"));
			pointer = stringBuilder.toString();
		}
		return pointer;
	}
}
