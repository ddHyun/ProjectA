package org.tourGo.models.community.review;

import javax.persistence.*;

import org.tourGo.models.entity.BaseEntity;
import org.tourGo.models.entity.FileInfo;

import lombok.*;

@Entity
@Table(name="review")
@Getter @Setter @ToString
public class ReviewEntity extends BaseEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int reviewNo;
	
	@ManyToOne(fetch=FetchType.LAZY)		//외래키는 참조테이블 자체를 객체로 받아오게 하고 쿼리로 필요데이터 사용
	@JoinColumn(name="user")				//(name="추가할컬럼명"), 실테이블에는 다른테이블의 기본키값이 들어감
	private User_test user_test;
	
	@Column(nullable=false, length=100)
	private String reviewTitle;
	@Column(nullable=false, length=20)
	private String region;
	@Column(nullable=false, length=20)
	private String period;
	@Column(columnDefinition="LONGTEXT", nullable=false)
	private String reviewContent;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="fileInfo")
//	private FileInfo fileInfo;
	
	@Column(length=45)	//파일들 그룹명(FileInfo에서 게시글 별 파일 조회할 때 유용)
	private String gid;
	
	private String fileName;	
	private int reviewRead;
	
}
