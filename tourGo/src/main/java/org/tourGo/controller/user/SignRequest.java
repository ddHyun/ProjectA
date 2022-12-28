package org.tourGo.controller.user;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Getter @Setter
public class SignRequest {
	
	@NotBlank(message="{NotBlank.userId}")
	@Size(min=6, message="{Size.userId}")
	private String userId;
	
	@NotBlank(message="{NotBlank.userPw}")
	@Size(min=8, message="{Size.userPw}")
	private String userPw;
	
	@NotBlank(message="{NotBlank.userPwRe}")
	private String userPwRe;
	
	@NotBlank(message="{NotBlank.userNm}")
	private String userNm;
	
	@NotBlank(message="{NotBlank.birth}")
	private String birth;
	
	@NotBlank(message="{NotBlank.email}")
	@Email(message="{Email.email}")
	private String email;
	
	@NotBlank(message="{NotBlank.mobile}")
	// @Pattern(regexp = "[0-9] {10, 11}")
	private String mobile;
	
	private String intro;
	
	private String gid;
	
	@AssertTrue
	private boolean check1;
	
	@AssertTrue
	private boolean check2;
	
	@AssertTrue
	private boolean checkAll;
}
