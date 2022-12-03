package org.tourGo.models.entity.plan;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;

import java.util.List;

import lombok.*;

@Getter @Setter
@Entity
public class Plan extends BaseEntity {
	@Id @GeneratedValue
	private Long plannerNo;
	private String title;
	private String planSize;
	private String planType;
	
	/**
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn("memId")
	private Member member;
	*/
	
	@OneToMany(mappedBy="plan", fetch=FetchType.LAZY)
	private List<PlanDetail> plainDetails;
	
}
