package org.tourGo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tourGo.common.JsonResult;

@RestControllerAdvice
public class CommonRestController {

	@ExceptionHandler(RuntimeException.class)
	public JsonResult<Object> handleException(RuntimeException e){
		
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(false);
		result.setMessage(e.getMessage());
		e.printStackTrace();
		
		return result;
	}
}
