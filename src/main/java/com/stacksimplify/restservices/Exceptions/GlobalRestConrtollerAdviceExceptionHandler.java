package com.stacksimplify.restservices.Exceptions;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RestControllerAdvice //remove comment and comment out CustomGlobalExceptionHanlder @ControllerAdvice
public class GlobalRestConrtollerAdviceExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails usernameNotFound(UsernameNotFoundException ex) {
		
		return new CustomErrorDetails(new Date(System.currentTimeMillis()),
				"From @RestControllerAdvice NOT FOUND ",
				ex.getMessage());
	}
}
