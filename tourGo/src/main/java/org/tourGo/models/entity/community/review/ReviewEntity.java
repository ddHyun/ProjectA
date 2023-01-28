package org.tourGo.models.entity.community.review;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEntity extends BaseEntity {

	@Id @GeneratedValue
	private Long reviewNo;						//글번호
	
	@ManyToOne(fetch=FetchType.LAZY)		
	@JoinColumn(name="userNo")				
	private User user;								//작성자(id&name)
	
	@Column(nullable=false, length=100)
	private String reviewTitle;					//제목
	@Column(nullable=false, length=20)
	private String region;						//여행지
	@Column(nullable=false, length=20)
	private String period;						//여행기간
	@Lob
	@Column(nullable=false)
	private String reviewContent;			//내용
	@Column(nullable=false, updatable=false)
	private String gid;								//file 그룹id
	@Column(columnDefinition = "int default '0'", insertable=false, updatable=false)
	private int reviewRead;						//조회수
	@Column(nullable=false, columnDefinition="char(1) default 'N'", insertable=false)
	private char deleteYn; 					// 삭제 여부
	
	@OrderBy("regDt desc")
	@OneToMany(mappedBy = "review", orphanRemoval = true)
	private List<ReplyEntity> replies = new ArrayList<>();			//댓글	
	
	@Column(columnDefinition = "int default '0'")
	private int totalLikes; 	//좋아요 총 개수
	
	public void setDeleteYn(char deleteYn) {
		this.deleteYn = deleteYn;
	}

	@Builder
	public ReviewEntity(Long reviewNo, User user, String reviewTitle, String region, String period,
									String reviewContent, String gid, int reviewRead, 
									int totalLikes, char deleteYn) {
		this.reviewNo = reviewNo;
		this.user = user;
		this.reviewTitle = reviewTitle;
		this.region = region;
		this.period = period;
		this.reviewContent = reviewContent;
		this.gid = gid;
		this.reviewRead = reviewRead;
		this.totalLikes = totalLikes;
		this.deleteYn = deleteYn;
	}	
}
