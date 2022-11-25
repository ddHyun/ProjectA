package org.tourGo.controller.main.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequest {
	private String userId;
	private String userPw;
	private String userNm;
	private String birth;
	private String email;
	private String mobile;
	private String intro;
}
