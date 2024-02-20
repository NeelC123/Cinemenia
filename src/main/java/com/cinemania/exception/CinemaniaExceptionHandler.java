package com.cinemania.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CinemaniaExceptionHandler {

	@ExceptionHandler(value = { CinemaniaExceptionNotFound.class })
	public ResponseEntity<Object> handleNotFoundException(CinemaniaExceptionNotFound channelAdminNotFound) {
		CinemaniaException adminException = new CinemaniaException(channelAdminNotFound.getMessage(),
				channelAdminNotFound.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(adminException, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(value = { CinemaniaUnauthorized.class })
	public ResponseEntity<Object> handleCreatedException(CinemaniaUnauthorized hubUnauthorized) {
		CinemaniaException adminException = new CinemaniaException(hubUnauthorized.getMessage(), hubUnauthorized.getCause(),
				HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(adminException, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = { CinemaniaForbidden.class })
	public ResponseEntity<Object> handleForbiddenException(CinemaniaForbidden hubUnauthorized) {
		CinemaniaException adminException = new CinemaniaException(hubUnauthorized.getMessage(), hubUnauthorized.getCause(),
				HttpStatus.FORBIDDEN);
		return new ResponseEntity<>(adminException, HttpStatus.FORBIDDEN);
	}

}
