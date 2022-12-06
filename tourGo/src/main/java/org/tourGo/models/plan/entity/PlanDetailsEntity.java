package org.tourGo.models.plan.entity;

import java.time.LocalTime;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

import lombok.*;

@Entity
@Data
public class PlanDetailsEntity extends BaseEntity {


	@Id @GeneratedValue
	private Long DetailNo;//db테이블 증감번호
	
/**	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plannerNo")
	private PlannerEntity plannerNo;//관계매핑
	*/
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
	    private String add;//관광지 주소
	
}

