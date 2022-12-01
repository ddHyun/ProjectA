package org.tourGo.controller.main.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.service.main.user.SignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/signUp")
@Slf4j
public class SignRestController {
	
	@Autowired
	private SignService signService;
	
	@PostMapping
	public void signUpPs(Model model, @Valid SignRequest request, Errors errors) {
		log.info("signUpPs {}", request.toString());
		// 비밀번호 확인 검증
		new SignValidator().validate(request, errors);
		if(errors.hasErrors()) {
			
		}
		
		signService.process(request);
	}
}
