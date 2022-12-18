package org.tourGo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tourGo.common.JsonResult;
import org.tourGo.common.exception.ErrorCode;
import org.tourGo.common.exception.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice("org.tourGo.controller")
public class ExceptionController {
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e, Model model) {
		
		JsonResult<String> result = new JsonResult<>();
		result.setSuccess(false);
		result.setMessage(e.getMessage());
		
		//alert창 띄운 후 이동할 경로 적기
		String script = "alert('"+e.getMessage()+"');";

		model.addAttribute("script", script);
		
		return "common/execution_script";
		
//		model.addAttribute("result", result);
		
//		return "common/errors";
	}	
	
	
	//예외 처리하기
	@ExceptionHandler(Exception.class)
	public String handleError(Exception e, Model model) {
		
		System.out.println("에러 Controller");
		model.addAttribute("message", e.getMessage());
		e.printStackTrace();
		
		return "common/errors"; 
	}
	
}
