package org.tourGo.controller.community.review;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.tourGo.models.entity.community.review.LikedEntity;
import org.tourGo.models.entity.community.review.ReplyEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewRequest {

	private Long reviewNo; 		//글번호
	private String id;			//아이디
	private String name;		//작성자
	@NotBlank
	private String reviewTitle;	//제목
	@NotBlank
	private String region;		//여행지
	@NotBlank
	private String period;		//기간
	@NotBlank
	private String reviewContent;	//내용
	private String gid;				//파일그룹id
	private LocalDateTime regDt;	//작성일
	private LocalDateTime modDt;	//수정일
	private int reviewRead;			//조회수
	private List<ReplyEntity> replies;	//댓글
	private List<LikedEntity> likes;		//좋아요
	
	public ReviewRequest() {}
	
	public ReviewRequest(ReviewEntity entity) {
		reviewNo = entity.getReviewNo();
		id = entity.getUser().getUserId();
		name = entity.getUser().getUserNm();
		reviewTitle = entity.getReviewTitle();
		region = entity.getRegion();
		period = entity.getPeriod();
		reviewContent = entity.getReviewContent();
		gid = entity.getGid();
		regDt = entity.getRegDt();
		modDt = entity.getModDt();
		reviewRead = entity.getReviewRead();	
		replies = entity.getReplies();
		likes = entity.getLikes();
	}	
}
