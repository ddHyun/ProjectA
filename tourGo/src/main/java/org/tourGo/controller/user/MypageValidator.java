package org.tourGo.controller.user;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.tourGo.models.entity.user.User;

public class MypageValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = new User();
		
		// 1. 새 비밀번호, 기존 비밀번호 동일 여부
	}
}
