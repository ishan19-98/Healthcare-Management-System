package com.bean;

public class ErrorResponse {

	public String message;
	
	public StackTraceElement[] stackTrace;

	
	
	public ErrorResponse(String message, StackTraceElement[] stackTrace) {
		super();
		this.message = message;
		this.stackTrace = stackTrace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	
}
