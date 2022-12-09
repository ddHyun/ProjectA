package org.tourGo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tourGo.common.JsonResult;

@RestControllerAdvice("controller")
public class CommonRestController {

	@ExceptionHandler(Exception.class)
	public JsonResult<Object> handleException(Exception e){
		
		System.out.println("에러 RestController");
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(false);
		result.setMessage(e.getMessage());
		e.printStackTrace();
		System.out.println(result);
		
		return result;
	}
}
