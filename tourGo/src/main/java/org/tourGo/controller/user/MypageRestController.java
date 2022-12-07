package org.tourGo.controller.user;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tourGo.common.ResponseDto;
import org.tourGo.models.entity.user.User;
import org.tourGo.service.user.MypageService;
import org.tourGo.service.user.ValidateHandleService;

@RestController
public class MypageRestController {
	
	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private ValidateHandleService validateHandleService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PutMapping("/user/api/updateMypage")
	public ResponseDto<?> myPageUpdate(@Valid @RequestBody MypageRequest request, Errors errors) {
		
		// 새 비밀번호 검증(미구현)
		// new MypageValidator().validate(request, errors);
		if(errors.hasErrors()) {
			Map<String, String> validatorResult = validateHandleService.validateHandling(errors);
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validatorResult);
		}
		mypageService.process(request);
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserId(), request.getUserPwNew()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}
}
