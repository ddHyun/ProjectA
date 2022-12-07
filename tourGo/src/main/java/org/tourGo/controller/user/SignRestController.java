package org.tourGo.controller.user;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.ResponseDto;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;
import org.tourGo.service.user.SignService;
import org.tourGo.service.user.ValidateHandleService;

@RestController
public class SignRestController {
	
	@Autowired
	private SignService signService;
	
	@Autowired
	private ValidateHandleService validateHandleService;
	
	@PostMapping("/user/api/signUp")
	public ResponseDto<?> signUpPs(@Valid @RequestBody SignRequest request, Errors errors) {
		// 비밀번호 확인 검증
		new SignValidator().validate(request, errors);
		if(errors.hasErrors()) {
			Map<String, String> validatorResult = validateHandleService.validateHandling(errors);
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validatorResult);
		}
		
		signService.process(request); 
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}
}
