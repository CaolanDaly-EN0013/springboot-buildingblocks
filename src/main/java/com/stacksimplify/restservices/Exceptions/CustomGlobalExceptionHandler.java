package com.stacksimplify.restservices.Exceptions;

import java.sql.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(System.currentTimeMillis()),
				"From MethodArgumentNotValid Exception",
				ex.getLocalizedMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(System.currentTimeMillis()),
				"From HttpRequestMethodNotSupported Exception",
				ex.getLocalizedMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<Object> handleUsernameNotFoundException(
			UsernameNotFoundException ex, WebRequest request) {
		
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(System.currentTimeMillis()),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleContraintViolationException(
			ConstraintViolationException ex, WebRequest request) {
		
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(System.currentTimeMillis()),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
}
