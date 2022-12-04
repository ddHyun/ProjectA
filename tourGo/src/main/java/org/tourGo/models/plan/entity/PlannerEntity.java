package org.tourGo.models.plan.entity;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;


import lombok.*;

@Getter @Setter
@Entity
public class PlannerEntity extends BaseEntity {
	@Id @GeneratedValue
	private Long plannerNo;
	private String title;
	private String planSize;
	private String planType;
	 private LocalDateTime sdate;
	 private LocalDateTime edate;
	 private String memId;
	 private String image;
	/**
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn("memId")
	private Member member;
	*/
	
	@OneToMany(mappedBy="plan", fetch=FetchType.LAZY)
	private List<PlanDetailsEntity> plainDetails;
	
}
