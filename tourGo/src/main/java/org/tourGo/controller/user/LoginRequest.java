package org.tourGo.controller.user;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@ToString
public class LoginRequest {
	
	@NotBlank(message="{NotBlank.userId}")
	private String userId;
	
	@NotBlank(message="{NotBlank.userPw}")
	private String userPw;
}
