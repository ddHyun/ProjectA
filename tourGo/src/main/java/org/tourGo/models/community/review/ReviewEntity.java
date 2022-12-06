package org.tourGo.models.community.review;

import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="review")
@Getter @Setter @ToString
public class ReviewEntity extends BaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reviewNo;
	
	@ManyToOne(fetch=FetchType.LAZY)		//외래키는 참조테이블 자체를 객체로 받아오게 하고 쿼리로 필요데이터 사용
	@JoinColumn(name="user")				//(name="추가할컬럼명"), 실테이블에는 해당테이블의 기본키값이 들어감
	private User user;
	
	@Column(nullable=false, length=100)
	private String reviewTitle;
	@Column(nullable=false, length=20)
	private String region;
	@Column(nullable=false, length=20)
	private String period;
	@Lob
	private String reviewContent;
	
	private String gid;	
	private int reviewRead;
	
}
