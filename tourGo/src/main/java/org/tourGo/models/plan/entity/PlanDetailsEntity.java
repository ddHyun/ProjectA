package org.tourGo.models.plan.entity;

import java.time.LocalTime;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

import lombok.*;

@Entity
@Getter @Setter
<<<<<<< HEAD:tourGo/src/main/java/org/tourGo/models/plan/entity/PlanDetailsEntity.java
public class PlanDetailsEntity extends BaseEntity {
=======
public class PlanDetail extends org.tourGo.common.BaseEntity {
>>>>>>> merge:tourGo/src/main/java/org/tourGo/models/entity/plan/PlanDetail.java
	@Id @GeneratedValue
	private Long DetailNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plannerNo")
	private PlannerEntity plan;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tourInfoId")
	private TourlistEntity tourInfo;
	
	private LocalTime sTime;
	private LocalTime eTime;
	
}
