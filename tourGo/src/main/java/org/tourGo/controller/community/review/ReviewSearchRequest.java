package org.tourGo.controller.community.review;

import lombok.*;

/**
 * [검색어 커맨드객체]
 * 현재는 필드가 하나지만
 * 추가될 필드의 가능성으로 커맨드 객체로 만듦 
 * 
 * */

@Getter @Setter @ToString
public class ReviewSearchRequest {
	
	private String keyword;
}
