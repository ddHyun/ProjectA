package org.tourGo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tourGo.common.JsonErrorResponse;
import org.tourGo.common.JsonException;

@RestControllerAdvice("restcontroller")
public class RestExceptionController {

	@ExceptionHandler(JsonException.class)
	public ResponseEntity<JsonErrorResponse> jsonErrorHandler(JsonException e) {
		JsonErrorResponse jer = e.getResponse();
		
		return ResponseEntity.status(jer.getStatus()).body(jer);
	}
}
