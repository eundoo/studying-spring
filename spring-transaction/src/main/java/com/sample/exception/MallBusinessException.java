package com.sample.exception;

public class MallBusinessException extends RuntimeException{

	public MallBusinessException(String message) {
		super(message);
	}
	
	public MallBusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
