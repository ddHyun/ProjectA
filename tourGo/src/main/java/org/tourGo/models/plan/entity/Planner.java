package org.tourGo.models.plan.entity;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.plan.TourType;
import org.tourGo.models.plan.details.PlanDetailsRq;

import java.time.LocalDate;
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
	@Enumerated(EnumType.STRING) 
	private TourType planType;
	 private LocalDate sdate;
	 private LocalDate edate;
	 private String image;
	 private String memo;
	private Integer day;
	 //(fetch=FetchType.LAZY) 임시로지움
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	
/**	@OneToMany(mappedBy="plan", fetch=FetchType.LAZY)
	private List<PlanDetailsEntity> plainDetails;*/
	
}
