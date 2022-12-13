package org.tourGo.models.plan.entity;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.details.PlanDetailsRq;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import lombok.*;


@Entity
@Table(name="planner")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Planner extends BaseEntity {
	@Id @GeneratedValue
	private Long plannerNo;
	private String title;
	private String planSize;
	private String planType;
	 private LocalDateTime sdate;
	 private LocalDateTime edate;
	 private String image;
	 private String memo;
	private int day;
	 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;

	
/**	@OneToMany(mappedBy="plan", fetch=FetchType.LAZY)
	private List<PlanDetailsEntity> plainDetails;*/
	
}
