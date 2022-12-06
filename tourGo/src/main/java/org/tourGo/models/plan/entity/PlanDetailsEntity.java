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
	
	/**@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plannerNo")*/
	private Long plannerNo;
	
	/**@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tourInfoId")
	private TourlistEntity tourInfo;*/
	
	private LocalTime sTime;
	private LocalTime eTime;
	private int day;
	 private String image;//관광지 이미지
	  private String name;//관광지이름
	    private Float x;//관광지좌표
	    private Float y;
	
}

