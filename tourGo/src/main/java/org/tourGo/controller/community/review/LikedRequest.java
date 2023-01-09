package org.tourGo.controller.community.review;

import org.tourGo.models.entity.community.review.LikedEntity;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class LikedRequest {

	private Long likedNo;			//식별자
	private Long reviewNo;			//게시글 번호
	private String userId;			//좋아요 클릭한 사람
	private boolean liked;			//좋아요 클릭 여부
	
	public LikedRequest(LikedEntity entity) {
		likedNo = entity.getLikedNo();
		reviewNo = entity.getReview().getReviewNo();
		userId = entity.getUser().getUserId();
		liked = entity.isLiked();
	}

	@Override
	public String toString() {
		return "LikedRequest [likedNo=" + likedNo + ", reviewNo=" + reviewNo + ", userId=" + userId + ", liked=" + liked
				+ "]";
	}
	
	
}
