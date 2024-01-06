package com.example.demo.exception;

public class PinCodeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PinCodeNotFoundException(String message)
	{
		super(message);
	}
}
