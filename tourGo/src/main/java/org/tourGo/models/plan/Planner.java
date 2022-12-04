package org.tourGo.models.plan;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Planner {//여행 일정짜기dto

	 private Long plannerNo;
	 private String title;
	 private LocalDateTime sdate;
	 private LocalDateTime edate;
	 private String planSize;
	 private String planType;
	 private String memId;
	 private String memo;
	 private String image;
	 
}
