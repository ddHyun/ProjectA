package org.tourGo.models.user;

import lombok.Getter;

@Getter
public enum UserType {
	USER("사용자", 1), // 일반회원
	ADMIN("관리자", 2), // 관리자
	SUPERADMIN("전체 관리자", 3); // 전체 관리자
	
	private String job;
	private int seq;
	
	UserType(String job, int seq) {
		this.job = job;
		this.seq = seq;
	}
}
