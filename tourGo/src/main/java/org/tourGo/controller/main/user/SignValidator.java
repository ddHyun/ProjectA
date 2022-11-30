package org.tourGo.controller.main.user;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SignValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SignRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SignRequest signRequest = (SignRequest) target;
		
		String userPw = signRequest.getUserPw();
		String userPwRe = signRequest.getUserPwRe();
		
		if(!userPw.equals(userPwRe)) {
			errors.rejectValue("userPwRe", "SignError", "비밀번호 확인에 실패했습니다.");
		}
	}
}
