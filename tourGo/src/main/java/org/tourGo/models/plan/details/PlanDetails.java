package org.tourGo.models.plan.details;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.tourGo.models.plan.Planner;
import org.tourGo.models.plan.entity.PlanDetailsEntity;
import org.tourGo.models.plan.tourList.TourList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanDetails {//여행 세부일정을 담당하는 dto
		
		private Long DetailsNo;
	    private Long plannerNo;

	    private LocalTime stime;
	
	    private LocalTime etime;
	    private String name;//관광지이름
	    private Double x;//관광지좌표
	    private Double y;
	    private int day;
	    private String image;//관광지 이미지
	    

	    
	    
	    public static PlanDetails planDetailsToPlanDetailsEntity(PlanDetailsEntity entity) {

	    	return PlanDetails.builder()
	    			.DetailsNo(entity.getDetailNo())
	    			//.plannerNo(entity.getPlannerNo().getPlannerNo())
	    			.day(entity.getDay())
	    			.stime(entity.getSTime())
	    			.etime(entity.getETime())
	    			.image(entity.getImage())
	    			.name(entity.getName())
	    			.x(entity.getX())
	    			.y(entity.getY()).build();
	    
	    }
	    
	    public static PlanDetailsEntity entityToPlanDeatils(PlanDetails planDetails) {

	    	return PlanDetailsEntity.builder()
	    			.DetailNo(planDetails.getDetailsNo())
	    			.day(planDetails.getDay())
	    			.sTime(planDetails.getStime())
	    			.eTime(planDetails.getEtime())
	    			.image(planDetails.getImage())
	    			.name(planDetails.getName())
	    			.x(planDetails.getX())
	    			.y(planDetails.getY()).build();
	    }
	    
}
