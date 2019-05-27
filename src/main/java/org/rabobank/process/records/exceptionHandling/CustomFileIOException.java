package org.rabobank.process.records.exceptionHandling;

import java.io.IOException;

public class CustomFileIOException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public CustomFileIOException(String message) {
		super(message);
	}

	public CustomFileIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomFileIOException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
