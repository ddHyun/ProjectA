package org.tourGo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tourGo.common.JsonResult;

@RestControllerAdvice("restcontroller")
public class RestExceptionController {

	@ExceptionHandler(RuntimeException.class)
	public JsonResult<Object> handleException(Exception e){
		
		System.out.println("에러 RestController");
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(false);
		result.setMessage(e.getMessage());
		e.printStackTrace();
		
		return result;
	}
}
