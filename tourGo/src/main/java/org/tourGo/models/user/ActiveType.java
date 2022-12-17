package org.tourGo.models.user;

import lombok.*;

@Getter
public enum ActiveType {
	ACTIVE("활동"), // 활동
	DORMENT("휴면"), // 휴면
	STOP("정지"); // 정지
	
	private String active;
	
	ActiveType(String active) {
		this.active = active;
	} 
}
