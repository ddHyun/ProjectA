package org.tourGo.models.plan.entity;

import java.time.LocalTime;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

import lombok.*;

@Entity
@Getter @Setter
public class PlanDetailsEntity extends BaseEntity {


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

