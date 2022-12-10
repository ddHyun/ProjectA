package org.tourGo.models.plan;

import java.time.LocalDateTime;
import java.util.List;

import org.tourGo.models.plan.details.PlanDetails;
import org.tourGo.models.plan.entity.PlannerEntity;

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
	 private String memId;//수정예정
	 private String memo;
	 private String image;//대표이미지
	
	 
	 public static Planner entityToPlanner(PlannerEntity entity) {
		 
		return Planner.builder().plannerNo(entity.getPlannerNo())
		 .planSize(entity.getPlanSize())
		 .planType(entity.getPlanType())
		 .sdate(entity.getSdate())
		 .edate(entity.getEdate())
		 .title(entity.getTitle())
		 .image(entity.getImage())
		 .memo(entity.getMemo()).build();
 
		/** Planner planner = new Planner();
		 planner.setPlannerNo(entity.getPlannerNo());
		 planner.setPlanSize(entity.getPlanSize());
		 planner.setPlanType(entity.getPlanType());
		 planner.setSdate(entity.getSdate());
		 planner.setEdate(entity.getEdate());
		 planner.setTitle(entity.getTitle());
		 planner.setImage(entity.getImage());
		// planner.setMemId(entity.getMemId());
		 planner.setMemo(entity.getMemo());*/
		

	 }
	 
	 public static PlannerEntity plannerToEntity(Planner planner) {

		 return PlannerEntity.builder().plannerNo(planner.getPlannerNo())
				 .planSize(planner.getPlanSize())
				 .planType(planner.getPlanType())
				 .sdate(planner.getSdate())
				 .edate(planner.getEdate())
				 .title(planner.getTitle())
				 .image(planner.getImage())
				 .memo(planner.getMemo()).build();
	/**	 entity.setPlannerNo(planner.getPlannerNo());
		 entity.setPlanSize(planner.getPlanSize());
		 entity.setPlanType(planner.getPlanType());
		 entity.setSdate(planner.getSdate());
		 entity.setEdate(planner.getEdate());
		 entity.setTitle(planner.getTitle());
		 entity.setImage(planner.getImage());
	//	 entity.setMemId(planner.getMemId());
		 entity.setMemo(planner.getMemo());
		 */
		 
	
	 }
	 
	 
	 
	 
}
