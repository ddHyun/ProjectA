package org.tourGo.controller.community.notice;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter @Setter
public class SearchRequest {
	
	private String searchType;
	
	private String searchKeyword;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate sdate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate edate;
}
