package org.tourGo.controller.community.review;

import org.tourGo.models.entity.community.review.LikedEntity;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class LikedRequest {

	private String uid;		//식별자(reviewNo_userNo)
	
	public LikedRequest(LikedEntity entity) {
		uid = entity.getUid();
	}	
}
