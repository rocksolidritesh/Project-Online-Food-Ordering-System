package com.example.demo.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.OrderStatusNotFound;
import com.example.demo.exception.PinCodeNotFoundException;
import com.example.demo.exception.CancelOrderException;
import com.example.demo.exception.OpeningClosingoneStopException;
import com.example.demo.exception.productNotFoundException;
import com.example.demo.model.ErrorModel;

import org.springframework.validation.FieldError;

@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
      @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	  Map<String, String> errors = new HashMap<>();
	  ex.getBindingResult().getAllErrors().forEach((error) ->{
	  String fieldName = ((FieldError) error).getField();
	  String message = error.getDefaultMessage();
	  errors.put(fieldName, message);
	  });

	     return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);

	   }


	
	
	@ExceptionHandler(productNotFoundException.class)
	public ResponseEntity<ErrorModel> handleproductNotFoundException(productNotFoundException ex, WebRequest req)
	{
		ErrorModel model = new ErrorModel(new Date(), ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
		
		
		
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(OpeningClosingoneStopException.class)
	public ResponseEntity<ErrorModel> handleoneStopException(OpeningClosingoneStopException ex, WebRequest req)
	{
		ErrorModel model = new ErrorModel(new Date(), ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
		
		
		
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(PinCodeNotFoundException.class)
	public ResponseEntity<ErrorModel> handlepinCodeNotFoundexception(PinCodeNotFoundException ex,WebRequest req)
	{
		ErrorModel model=new ErrorModel(new Date(),ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorModel> handleordernotfoundexception(OrderNotFoundException ex,WebRequest req)
		{
		ErrorModel model=new ErrorModel(new Date(),ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
		
	}
		
	@ExceptionHandler(OrderStatusNotFound.class)
	public ResponseEntity<ErrorModel> handleOrderStatusnotfoundexception(OrderNotFoundException ex,WebRequest req)
	{
	ErrorModel model=new ErrorModel(new Date(),ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
	return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CancelOrderException.class)
	public ResponseEntity<ErrorModel> handlecancelorderexception(CancelOrderException ex,WebRequest req)
	{
		ErrorModel model=new ErrorModel(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.value(), req.getDescription(false));
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
	}
	
}
