package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bean.ErrorResponse;
import com.exception.ResourceNotFoundException;
import com.exception.GlobalException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception exp)
	{
		ErrorResponse errorResponse = new ErrorResponse(exp.getMessage(),exp.getStackTrace());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAppointmentNotFoundException(ResourceNotFoundException exp)
	{
		ErrorResponse errorResponse = new ErrorResponse(exp.getMessage(),exp.getStackTrace());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
