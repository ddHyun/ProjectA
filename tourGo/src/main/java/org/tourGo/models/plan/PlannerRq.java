package org.tourGo.models.plan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.tourGo.models.plan.details.PlanDetailsRq;
import org.tourGo.models.plan.entity.Planner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlannerRq {//여행 일정짜기dto

	 private Long plannerNo;
	 @NotBlank(message="여행제목을 입력하세요.")
	 private String title;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private LocalDate sdate;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private LocalDate edate;
	 private String planSize;
	 private TourType planType;
	 private String memId;//수정예정
	 private String memo;
	 private String image;//대표이미지
	 private Integer day;
	 
	
	 
	 
	 
}
