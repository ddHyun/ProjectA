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
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long plannerNo; //자동증감번호
	private String title; //사용자 정의 제목
	private String planSize;	 //여행 인원수
	@Enumerated(EnumType.STRING) 
	private TourType planType; // enum으로 여행 타입 설정
	 private LocalDate sdate; //시작날짜
	 private LocalDate edate; //종료날짜
	 private String image; // planDetails에서 가장첫번째 이미지 추출(예정)
	 private String memo; // 사용자 정의 여행 메모
	private Integer day; // 여행일 (1,2,3,4...)
	 //(fetch=FetchType.LAZY) 임시로지움
	@ManyToOne
	@JoinColumn(name="userNo")
	private User user; //유저 pk값이랑 관계매핑
	private Integer heart; //추천수
	private Integer hit; //조회수
	private Boolean open;//공개,비공개 여부
	@Column(columnDefinition = "integer default 0", nullable = false)
    private int view; // 조회수
	
/**	@OneToMany(mappedBy="plan", fetch=FetchType.LAZY)
	private List<PlanDetailsEntity> plainDetails;*/
	
}
