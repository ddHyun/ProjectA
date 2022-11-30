package org.tourGo.controller.main.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
public class SignRequest {
	
	@NotBlank
	@Size(min=6)
	private String userId;
	
	@NotBlank
	@Size(min=8)
	private String userPw;
	
	@NotBlank
	private String userPwRe;
	
	@NotBlank
	private String userNm;
	
	@NotBlank
	private String birth;
	
	@Email
	private String email;
	
	@NotBlank
	private String mobile;
	private String intro;
}
