package org.tourGo.models.entity.community.review;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.tourGo.common.BaseEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="review")
@Getter @Setter
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
	
	@OrderBy("regDt desc")
	@OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
	private List<ReplyEntity> reply;			//댓글	

	@Override
	public String toString() {
		return "ReviewEntity [reviewNo=" + reviewNo + ", user=" + user + ", reviewTitle=" + reviewTitle + ", region="
				+ region + ", period=" + period + ", reviewContent=" + reviewContent + ", gid=" + gid + ", reviewRead="
				+ reviewRead + ", hash=" + "]";
	}
}
