package org.tourGo.service.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.tourGo.controller.user.SignRequest;
import org.tourGo.models.user.User;
import org.tourGo.models.user.UserDao;

@Service
public class SignService {
	@Autowired
	private UserDao userDao;
	
	public void process(SignRequest request) {
		
		User user = new User();
		user.setUserId(request.getUserId());
		user.setUserPw(request.getUserPw());
		user.setUserNm(request.getUserNm());
		user.setBirth(request.getBirth());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setIntro(request.getIntro());
		
		boolean result = userDao.register(user);
		if(!result) {
			throw new RuntimeException("회원 가입 실패!");
		}
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
