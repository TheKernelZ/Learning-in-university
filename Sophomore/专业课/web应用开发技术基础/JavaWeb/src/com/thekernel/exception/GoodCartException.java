package com.thekernel.exception;

public class GoodCartException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GoodCartException() {
		super();
	}

	public GoodCartException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GoodCartException(String message, Throwable cause) {
		super(message, cause);
	}

	public GoodCartException(String message) {
		super(message);
	}

	public GoodCartException(Throwable cause) {
		super(cause);
	}
	
}
