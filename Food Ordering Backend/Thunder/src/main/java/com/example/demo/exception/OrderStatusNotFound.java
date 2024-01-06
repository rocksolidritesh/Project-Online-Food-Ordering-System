package com.example.demo.exception;


public class OrderStatusNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OrderStatusNotFound(String message)
	{
		super(message);
	}

}
