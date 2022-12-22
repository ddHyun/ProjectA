package org.tourGo.models.plan.details;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;
import org.tourGo.models.plan.PlannerRq;
import org.tourGo.models.plan.entity.PlanDetails;
import org.tourGo.models.plan.tourList.TourList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanDetailsRq {//여행 세부일정을 담당하는 dto
		
		private Long DetailsNo;
	    private Long plannerNo;
	    @DateTimeFormat(pattern="HH : mm")
	    private LocalTime stime;
	
	    private LocalTime etime;
	    private String name;//관광지이름
	    private String address;
	    private int day;
	    private String image;//관광지 이미지
	    

	    
	    
	    public static PlanDetailsRq planDetailsToPlanDetailsEntity(PlanDetails entity) {

	    	return PlanDetailsRq.builder()
	    			.DetailsNo(entity.getDetailNo())
	    			//.plannerNo(entity.getPlannerNo().getPlannerNo())
	    			.day(entity.getDay())
	    			.stime(entity.getSTime())
	    			.etime(entity.getETime())
	    			.image(entity.getImage())
	    			.name(entity.getName())
	    			.build();
	    
	    }
	    
	    public static PlanDetails entityToPlanDeatils(PlanDetailsRq planDetails) {

	    	return PlanDetails.builder()
	    			.DetailNo(planDetails.getDetailsNo())
	    			.day(planDetails.getDay())
	    			.sTime(planDetails.getStime())
	    			.eTime(planDetails.getEtime())
	    			.image(planDetails.getImage())
	    			.name(planDetails.getName())
	    			.build();
	    }
	    
}
