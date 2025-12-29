package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.bean.ErrorResponse;
import com.exception.ResourceNotFoundException;
import com.exception.ConflictException;
import com.exception.GlobalException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception exp)
	{
		ErrorResponse errorResponse = new ErrorResponse(exp.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAppointmentNotFoundException(ResourceNotFoundException exp)
	{
		ErrorResponse errorResponse = new ErrorResponse(exp.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflictException(ConflictException exp)
	{
		ErrorResponse errorResponse = new ErrorResponse(exp.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp)
	{
		ErrorResponse errorResponse = new ErrorResponse(exp.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ErrorResponse> handleHandlerMethodValidationExceptionException(HandlerMethodValidationException exp)
	{
		ErrorResponse errorResponse = new ErrorResponse(exp.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);	
	}



}
