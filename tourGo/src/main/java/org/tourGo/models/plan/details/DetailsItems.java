package org.tourGo.models.plan.details;

import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailsItems {//ajax로 요청받기위한 dto객체


	// @DateTimeFormat(pattern="HH : mm")
	private List<String> stime;
	// @DateTimeFormat(pattern="HH : mm")
	private List<String> etime;

	private List<Long> detailsNo;
	private Long plannerNo;
	private Integer day;
	
}
