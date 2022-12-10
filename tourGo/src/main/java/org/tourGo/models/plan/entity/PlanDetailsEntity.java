package org.tourGo.models.plan.entity;

import java.time.LocalTime;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.plan.details.PlanDetails;

import lombok.*;

@Builder
@Entity
@Table(name="planDetails")
@Getter @Setter
public class PlanDetailsEntity extends BaseEntity {


	@Id @GeneratedValue
	private Long DetailNo;//db테이블 증감번호
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plannerNo")
	private PlannerEntity planner;//관계매핑
	
	private LocalTime sTime;
	private LocalTime eTime;
	@Column(nullable=true)
	private int day;
	 private String image;//관광지 이미지
	  private String name;//관광지이름
	  private String address;//관광지 주소
	
}

