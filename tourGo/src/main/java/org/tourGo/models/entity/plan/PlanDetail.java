package org.tourGo.models.entity.plan;

import java.time.LocalTime;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

import lombok.*;

@Entity
@Getter @Setter
public class PlanDetail extends org.tourGo.common.BaseEntity {
	@Id @GeneratedValue
	private Long DetailNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plannerNo")
	private Plan plan;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tourInfoId")
	private TourInfo tourInfo;
	
	private LocalTime sTime;
	private LocalTime eTime;
	
}
