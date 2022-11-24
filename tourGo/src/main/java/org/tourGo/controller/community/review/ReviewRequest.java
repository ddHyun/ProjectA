package org.tourGo.controller.community.review;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;
import org.tourGo.models.community.review.ReviewDto;

import lombok.*;

@Getter @Setter
public class ReviewRequest {

	private int reviewNo; 		//글번호
	private String id;			//아이디
	private String name;		//작성자
	private String reviewTitle;	//제목
	private String region;		//여행지
	private String period;		//기간
	private String reviewContent;	//내용
	private String fileName;		//사진파일명
	private LocalDateTime regDt;	//작성일
	private LocalDateTime modDt;	//수정일
	private int reviewRead;			//조회수
	
	private MultipartFile imageFile;	//실제파일	
	
	//dto -> 커맨드로 바꾸기
	public static ReviewRequest toRequest(ReviewDto dto) {
		ReviewRequest request = new ReviewRequest();
		request.setReviewNo(dto.getReviewNo());
		request.setId(dto.getId());
		request.setName(dto.getName());
		request.setReviewTitle(dto.getReviewTitle());
		request.setRegion(dto.getRegion());
		request.setPeriod(dto.getPeriod());
		request.setReviewContent(dto.getReviewContent());
		request.setFileName(dto.getFileName());
		request.setRegDt(dto.getRegDt());
		request.setReviewRead(dto.getReviewRead());
		
		return request;
	}
	
}
