package org.tourGo.controller.community.review;

import java.time.LocalDateTime;

import org.tourGo.models.community.review.ReviewDto;

import lombok.*;

@Getter @Setter @ToString
public class ReviewRequest {

	private int reviewNo; 		//글번호
	private String id;			//아이디
	private String name;		//작성자
	private String reviewTitle;	//제목
	private String region;		//여행지
	private String period;		//기간
	private String reviewContent;	//내용
	private String fileName;		//사진파일명
	private String gid;
	private LocalDateTime regDt;	//작성일
	private LocalDateTime modDt;	//수정일
	private int reviewRead;			//조회수
	
	//dto -> 커맨드로 바꾸기
	public static ReviewRequest toRequest(ReviewDto dto) {
		ReviewRequest request = new ReviewRequest();
		request.setReviewNo(dto.getReviewNo());
		request.setId(dto.getUser_test().getId());
		request.setName(dto.getUser_test().getName());
		request.setReviewTitle(dto.getReviewTitle());
		request.setRegion(dto.getRegion());
		request.setPeriod(dto.getPeriod());
		request.setReviewContent(dto.getReviewContent());
//		request.setFileName(dto.getFileName());
		request.setGid(dto.getGid());
		request.setRegDt(dto.getRegDt());
		request.setReviewRead(dto.getReviewRead());
		
		return request;
	}
	
}
