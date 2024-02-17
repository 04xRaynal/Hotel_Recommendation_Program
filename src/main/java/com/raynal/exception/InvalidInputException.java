package com.raynal.exception;

public class InvalidInputException extends RuntimeException {
	private static final long serialVersionUID = 4404481776760893178L;

	public InvalidInputException(final String errorMessage) {
		super(errorMessage);
	}
}
