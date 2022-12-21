package org.tourGo.controller.admin;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter @Setter
public class AdminSearchRequest {
	
	private String searchType;
	
	private String searchKeyword;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate sdate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate edate;
}
