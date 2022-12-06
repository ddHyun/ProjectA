package org.tourGo.models.plan.details;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
	    private Float x;//관광지좌표
	    private Float y;
	    private int day;
	    private String image;//관광지 이미지
	    

	    
	    
	    public PlanDetails planDetailsToPlanDetailsEntity(PlanDetailsEntity entity) {
	    	
	    	PlanDetails planDetails = new PlanDetails();
	    	planDetails.setDetailsNo(entity.getDetailNo());
	    	planDetails.setPlannerNo(entity.getPlannerNo());
	    	planDetails.setDay(entity.getDay());
	    	planDetails.setStime(entity.getSTime());
	    	planDetails.setEtime(entity.getETime());
	    	planDetails.setImage(entity.getImage());
	    	planDetails.setName(entity.getName());
	    	planDetails.setX(entity.getX());
	    	planDetails.setY(entity.getY());
	    
	    	
	    	return planDetails;
	    }
	    
	    public PlanDetailsEntity entityToPlanDeatils(PlanDetails planDetails) {
	    	PlanDetailsEntity entity = new PlanDetailsEntity();
	    	
	    	entity.setDetailNo(planDetails.getDetailsNo());
	    	entity.setPlannerNo(planDetails.getPlannerNo());
	    	entity.setDay(planDetails.getDay());
	    	entity.setSTime(planDetails.getStime());
	    	entity.setETime(planDetails.getEtime());
	    	entity.setImage(planDetails.getImage());
	    	entity.setName(planDetails.getName());
	    	entity.setX(planDetails.getX());
	    	entity.setY(planDetails.getY());
	    	
	    	return entity;
	    }
	    
}
