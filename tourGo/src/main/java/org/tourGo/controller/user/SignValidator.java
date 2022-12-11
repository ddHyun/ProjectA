package org.tourGo.controller.user;

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
		
		// 1. 비밀번호 확인 비교
		String userPw = signRequest.getUserPw();
		String userPwRe = signRequest.getUserPwRe();
		
		if(!userPw.equals(userPwRe)) {
			errors.rejectValue("userPwRe", "SignError", "비밀번호 확인에 실패했습니다.");
		}
		
		// 2. 휴대폰번호 정규식 체크
		String mobile = signRequest.getMobile();
		if(mobile != null && !mobile.isBlank()) { // 휴대전화번호는 필수 X, 값이 있는 경우만 체크
			// 01000000000 010 0000 0000 / 010-0000-0000 / 010_0000 \d -> [0-9], \D -> [^0-9]
			mobile = mobile.replaceAll("\\D", ""); // 숫자가 아닌 문자를 제거 -> 숫자
			String pattern = "01[06]\\d{3,4}\\d{4}";
			if(!mobile.matches(pattern)) { // 휴대전화 번호 형식 X
				errors.rejectValue("mobile", "incorrect.mobile", "휴대전화번호 형식이 아닙니다.");
			}
		}
	}
}
