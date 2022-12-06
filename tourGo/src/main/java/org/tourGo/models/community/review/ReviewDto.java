package org.tourGo.models.community.review;

import java.time.LocalDateTime;

import org.tourGo.models.entity.user.User;

import lombok.*;

@Getter @Setter
public class ReviewDto {

	private int reviewNo;
	private User user; //outer join으로 테이블 병합 결과 필요
	private String reviewTitle;
	private String region;
	private String period;
	private String reviewContent;
	private String gid;
	private LocalDateTime regDt;
	private LocalDateTime modDt;
	private int reviewRead;
	
	
	//entity -> dto로 변환 : 객체로 받는 데이터들 주의!
	public static ReviewDto entityToDto(ReviewEntity entity) {
		ReviewDto dto = new ReviewDto();
		dto.setReviewNo(entity.getReviewNo());
		dto.setUser(entity.getUser());
		dto.setReviewTitle(entity.getReviewTitle());
		dto.setRegion(entity.getRegion());
		dto.setPeriod(entity.getPeriod());
		dto.setReviewContent(entity.getReviewContent());
		dto.setGid(entity.getGid());
		dto.setRegDt(entity.getRegDt());
		dto.setReviewRead(entity.getReviewRead());		
		
		return dto;
	}
	
	//dto -> entity로 바꾸기
	public static ReviewEntity dtoToEntity(ReviewDto dto) {
		ReviewEntity entity = new ReviewEntity();
		entity.setUser(dto.getUser());
		entity.setReviewTitle(dto.getReviewTitle());
		entity.setRegion(dto.getRegion());
		entity.setPeriod(dto.getPeriod());
		entity.setReviewContent(dto.getReviewContent());
		entity.setGid(dto.getGid());
		
		return entity;
	}
}
