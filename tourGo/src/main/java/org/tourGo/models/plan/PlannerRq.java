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

	 private Long plannerNo; //pk
	 @NotBlank
	 private String title; // 사용자 지정 제목
	 
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private LocalDate sdate; //여행 시작날짜
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private LocalDate edate;// 여행 종료날짜
	 private String planSize; // 여행인원수
	 private TourType planType; // 여행타입 체크박스 
	 private String memo; // 사용자 정의 메모
	 private String image;//대표이미지
	 private Integer day;//사용자 지정 일수 
	 private String heart; // 좋아요
	 private String hit; //조회수
	 
	 
	
	 
	 
	 
}
