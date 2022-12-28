package org.tourGo.models.entity.community.review;

import javax.persistence.*;

import org.tourGo.models.entity.user.User;

import lombok.*;

@Entity
@Table(name="liked")
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LikedEntity {

	@Id @GeneratedValue
	private Long likedNo;				//식별자
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userNo")	//좋아요 누른 사람
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reviewNo") //좋아요 누른 게시글 번호
	private ReviewEntity review;
	
	private boolean liked;				//좋아요 클릭 여부
	
	@Builder
	public LikedEntity(Long likedNo, User user, ReviewEntity review, boolean liked) {
		this.likedNo = likedNo;
		this.user = user;
		this.review = review;
		this.liked = liked;
	}
	
	public LikedEntity update(boolean liked) {
		this.liked = liked;
		return this;
	}

	@Override
	public String toString() {
		return "LikedEntity [likedNo=" + likedNo + ", liked=" + liked + ", user="+user.getUserId()+", review="+review.getReviewNo()+"]";
	}
	
	
}
