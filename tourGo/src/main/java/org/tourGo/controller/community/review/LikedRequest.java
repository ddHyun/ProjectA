package org.tourGo.controller.community.review;

import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.models.entity.user.User;

import lombok.*;

@Getter @Setter
public class LikedRequest {

	private Long likedNo;			//식별자
	private ReviewEntity review;	//게시글 번호
	private User user;					//좋아요 클릭한 사람
	private int likedTotal;			//좋아요 개수
	private boolean liked;			//좋아요 클릭 유무
}
