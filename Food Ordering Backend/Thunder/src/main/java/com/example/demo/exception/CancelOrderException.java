package com.example.demo.exception;

public class CancelOrderException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CancelOrderException(String message)
	{
		super(message);
	}

}
