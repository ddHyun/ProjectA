package org.tourGo.controller.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @ToString
public class MypageRequest {
	
	private Long userNo;
	
	private String userId;
	
	/*
	@NotBlank(message="{NotBlank.userPw}")
	private String userPw;
	*/
	@NotBlank(message="{NotBlank.userPwNew}")
	private String userPwNew;
	
	@NotBlank(message="{NotBlank.birth}")
	private String birth;
	
	@NotBlank(message="{NotBlank.email}")
	@Email(message="{Email.email}")
	private String email;
	
	@NotBlank(message="{NotBlank.mobile}")
	private String mobile;
	
	private String intro;
}
