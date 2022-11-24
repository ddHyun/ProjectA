package org.tourGo.commons;

import lombok.*;

@Getter @Setter @ToString
public class SearchRequest {
	//검색어 조회용 커맨드객체	
	
	private String keyword;		//검색 키워드
	private String searchType;	//검색 유형(전체/제목/내용/작성자 등)
		
}
