package org.tourGo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tourGo.common.exception.ErrorCode;
import org.tourGo.common.exception.ErrorResponse;
import org.tourGo.common.exception.NoSuchDataException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice("org.tourGo.controller")
public class ExceptionController {
	
	@ExceptionHandler(NoSuchDataException.class)
	public String handleNoSuchDataException(NoSuchDataException e, Model model) {
		ErrorResponse response = new ErrorResponse(ErrorCode.DATA_NOT_FOUND);
		System.out.println("=======================");
		System.out.printf("response.code : %s%n", response.getCode());
		System.out.printf("response.status : %s%n", response.getStatus());
		System.out.printf("response.message : %s%n", response.getMessage());
		ResponseEntity<ErrorResponse> result = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
		model.addAttribute("result", result);
		
		return "common/errors";
		//return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
