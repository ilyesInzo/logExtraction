package com.sap.rnd.logExtraction.exception;

public class RequestProcessException extends Exception{

	private static final long serialVersionUID = 1L;

	public RequestProcessException(String message) {
		super(message);
	}

	public RequestProcessException(String message, Throwable cause) {
		super(message, cause);
	}

}
