package org.tourGo.models.community.review;

import javax.persistence.*;

import org.tourGo.models.entity.BaseEntity;

import lombok.*;

@Entity
@Table(name="review")
@Getter @Setter @ToString
public class ReviewEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reviewNo;
	
	@ManyToOne(fetch=FetchType.LAZY)		//외래키는 참조테이블 자체를 객체로 받아오게 하고 쿼리로 필요데이터 사용
	@JoinColumn(name="id")				//(name="추가할컬럼명"), 실테이블에는 다른테이블의 기본키값이 들어감
	private User_test user_test;
	
	@Column(nullable=false, length=100)
	private String reviewTitle;
	@Column(nullable=false, length=20)
	private String region;
	@Column(nullable=false, length=20)
	private String period;
	@Column(columnDefinition="LONGTEXT", nullable=false)
	private String reviewContent;
	private String fileName;
	private int reviewRead;
	
}
