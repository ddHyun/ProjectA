package org.tourGo.models.user;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
	private long uNo;
	private String uId;
	private String uPw;
	private String uNm;
	private String birth;
	private String email;
	private String mobile;
	private String intro;
	private LocalDateTime regDt;
	private LocalDateTime modDt;
	private int activeType;
	private String adminYn;
	private String deleteYn;
}
