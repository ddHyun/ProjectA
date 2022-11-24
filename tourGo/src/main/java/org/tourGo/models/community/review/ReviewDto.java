package org.tourGo.models.community.review;

import java.time.LocalDateTime;

import lombok.*;

@Getter @Setter
public class ReviewDto {

	private int reviewNo;
	private String id;
	private String name; //outer join으로 테이블 병합 결과 필요
	private String reviewTitle;
	private String region;
	private String period;
	private String reviewContent;
	private String fileName;
	private LocalDateTime regDt;
	private LocalDateTime modDt;
	private int reviewRead;
	
	
	//entity -> dto로 변환
	public static ReviewDto toDto(ReviewEntity entity) {
		ReviewDto dto = new ReviewDto();
		dto.setReviewNo(entity.getReviewNo());
		dto.setId(entity.getUser_test().getId());
		dto.setName(entity.getUser_test().getName());
		dto.setReviewTitle(entity.getReviewTitle());
		dto.setRegion(entity.getRegion());
		dto.setPeriod(entity.getPeriod());
		dto.setReviewContent(entity.getReviewContent());
		dto.setFileName(entity.getFileName());
		dto.setRegDt(entity.getRegDt());
		dto.setReviewRead(entity.getReviewRead());
		
		return dto;
	}
}
