package org.tourGo.models.user;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
	private long userNo;
	private String userId;
	private String userPw;
	private String userNm;
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
