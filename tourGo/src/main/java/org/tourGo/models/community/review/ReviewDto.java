package org.tourGo.models.community.review;

import java.time.LocalDateTime;
import java.util.List;

import org.tourGo.models.entity.FileInfo;

import lombok.*;

@Getter @Setter
public class ReviewDto {

	private int reviewNo;
	private User_test user_test; //outer join으로 테이블 병합 결과 필요
	private String reviewTitle;
	private String region;
	private String period;
	private String reviewContent;
	private List<FileInfo> fileInfo;
	private String gid;
	private LocalDateTime regDt;
	private LocalDateTime modDt;
	private int reviewRead;
	
	
	//entity -> dto로 변환 : 객체로 받는 데이터들 주의!
	public static ReviewDto toDto(ReviewEntity entity) {
		ReviewDto dto = new ReviewDto();
		dto.setReviewNo(entity.getReviewNo());
		dto.setUser_test(entity.getUser_test());
		dto.setReviewTitle(entity.getReviewTitle());
		dto.setRegion(entity.getRegion());
		dto.setPeriod(entity.getPeriod());
		dto.setReviewContent(entity.getReviewContent());
		dto.setGid(entity.getGid());
//		if(entity.getFileInfo()!=null) {
//			for(int i=0; i<entity.getFileInfo())
//		}
		dto.setRegDt(entity.getRegDt());
		dto.setReviewRead(entity.getReviewRead());
		
		return dto;
	}
}
