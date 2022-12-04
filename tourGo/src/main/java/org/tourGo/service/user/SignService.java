package org.tourGo.service.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.tourGo.controller.user.SignRequest;
import org.tourGo.models.entity.user.User;

@Service
public class SignService {
	
	public User process(SignRequest request) {
		
		User user = new User();
		user.setUserId(request.getUserId());
		user.setUserPw(request.getUserPw());
		user.setUserNm(request.getUserNm());
		user.setBirth(request.getBirth());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setIntro(request.getIntro());
		
		return user;
	}
	
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
}
